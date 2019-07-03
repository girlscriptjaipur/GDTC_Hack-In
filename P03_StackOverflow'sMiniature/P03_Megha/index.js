const express = require("express");
const mongoose = require("mongoose");
const bodyparser = require("body-parser");
const passport = require("passport");
const User = require("./models/User");
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
  res.status(200).send(`Hi Welcome to the Login and Signup API`);
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
    res.json({
      id: req.user.id,
      email: req.user.email
    });
  }
);

app.listen(port, () => {
  console.log(`Server is listening on port ${port}`);
});
