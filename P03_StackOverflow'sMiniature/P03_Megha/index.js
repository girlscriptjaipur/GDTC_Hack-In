const express = require("express");
const mongoose = require("mongoose");
const bodyparser = require("body-parser");
const passport = require("passport");
const User = require("./models/User");
const Question = require("./models/Question");
const db = require("./mysetup/myurl").myurl;
const bcrypt = require("bcrypt");
const jsonwt = require("jsonwebtoken");

const saltRounds = 10;
const app = express();
const key = require("./mysetup/myurl").secret;
const port = process.env.PORT || 3000;

app.use(bodyparser.urlencoded({ extended: false }));
app.use(bodyparser.json());

mongoose
  .connect(db)
  .then(() => {
    console.log("Database is connected");
  })
  .catch(err => {
    console.log("Error is ", err.message);
  });

app.use(passport.initialize());

require("./strategies/jsonwtStrategy")(passport);

app.get("/", (req, res) => {
  res.json({ message: "pong" });
});

app.post("/api/auth/register", async (req, res) => {
  const newUser = new User({
    name: req.body.name,
    email: req.body.email,
    password: req.body.password
  });

  const profile = await User.findOne({ email: newUser.email }).exec();
  if (profile) {
    return res.send("User already exists...");
  }

  const hashedPassword = await bcrypt.hash(newUser.password, saltRounds);
  newUser.password = hashedPassword;

  await newUser.save();

  res.status(200).send({
    success: true
  });
});

app.post("/api/auth/login", async (req, res) => {
  const user = {
    email: req.body.email,
    password: req.body.password
  };

  const profile = await User.findOne({ email: user.email }).exec();
  if (!profile) {
    return res.send("User not exist");
  }

  const result = bcrypt.compare(user.password, profile.password);
  if (!result) {
    res.send("Login incorrect");
  }
  //   res.send("User authenticated");
  const payload = {
    id: profile.id,
    email: profile.email
  };
  jsonwt.sign(payload, key, { expiresIn: 3600 }, (err, token) => {
    console.log(err);
    res.json({
      success: true,
      token: "Bearer " + token
    });
  });
});

app.get(
  "/api/auth/profile",
  passport.authenticate("jwt", { session: false }),
  (req, res) => {
    User.findById(req.user.id)
      .lean()
      .then(user => {
        delete user.password;
        res.json(user);
      });
  }
);
//get(public route) route to get all the Questions.
app.get(
  "/api/questions",
  passport.authenticate("jwt", { session: false }),
  (req, res) => {
    Question.find()
      .sort({ date: "desc" })
      .lean()
      .then(questions =>
        res.json(
          questions.map(q => {
            const question = { ...q };
            delete question.answers;
            return question;
          })
        )
      )
      .catch(err => res.json({ noquestions: "No Questions" }));
  }
);

//route(private route) to post the Question.
app.post(
  "/api/questions",
  passport.authenticate("jwt", { session: false }),
  (req, res) => {
    const newQuestion = new Question({
      text: req.body.text,
      user: req.user.id,
      name: req.body.name
    });
    newQuestion
      .save()
      .then(question => res.json(question))
      .catch(err => console.log("unable to push question"));
  }
);

app.get(
  "/api/questions/:id",
  passport.authenticate("jwt", { session: false }),
  (req, res, next) => {
    Question.findById(req.params["id"])
      .lean()
      .then(question => {
        delete question.answers;
        res.json(question);
      })
      .catch(err => next(err));
  }
);

//public route to get all the Answers
app.get(
  "/api/questions/:id/answers",
  passport.authenticate("jwt", { session: false }),
  (req, res, next) => {
    Question.findById(req.params["id"])
      .lean()
      .then(question => {
        res.json(question.answers);
      })
      .catch(err => next(err));
  }
);

//private route to post the Answer only for the existing Questions.
app.post(
  "/api/questions/:id/answers",
  passport.authenticate("jwt", { session: false }),
  (req, res) => {
    Question.findById(req.params.id)
      .then(question => {
        const newAnswer = {
          user: req.user.id,
          name: req.body.name,
          text: req.body.text
        };
        question.answers.unshift(newAnswer);
        return question.save();
      })
      .then(question => res.json(question))
      .catch(err => console.log(err));
  }
);

app.post("/api/questions/:id/answers/:answer_id/upvote", (req, res, next) => {
  const answerId = parseInt(req.params["answer_id"]);
  Question.findById(req.params.id)
    .then(question => {
      console.log(question);
      question.answers[answerId].votes++;
      return question.save();
    })
    .then(question => res.json(question.answers[answerId]))
    .catch(next);
});

app.use((error, req, res, next) => {
  console.log("Server encountered error", error);
  res.json({
    error,
    message: error.message || error || "Not found"
  });
});

app.listen(port, () => {
  console.log(`Server is listening on port ${port}`);
});
