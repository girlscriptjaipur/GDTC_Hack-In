var express = require("express");
var mongoose = require("mongoose");
var bodyparser = require("body-parser");
var User = require("./models/User");
var Question = require("./models/Question");
var passport = require("passport");
var LocalStrategy = require("passport-local");
var db = require("./mysetup/myurl").myurl;
// var bcrypt = require('bcryptjs');
// var saltRounds = 10;
var app = express();

var port = process.env.PORT || 3000;
// const hostname = '127.0.0.1';
// const port = 3000;

app.use(bodyparser.urlencoded({ extended: true }));
app.set("view engine","ejs");
//app.use(bodyparser.json());

mongoose
  .connect(db)
  .then(() => {
    console.log("Database is connected");
  })
  .catch(err => {
    console.log("Error is ", err.message);
  });

// PASSPORT CONFIGURATION
app.use(require("express-session")({
    secret: "I am Cosmogod",
    resave: false,
    saveUninitialized: false
}));
app.use(passport.initialize());
app.use(passport.session());
passport.use(new LocalStrategy(User.authenticate()));
passport.serializeUser(User.serializeUser());
passport.deserializeUser(User.deserializeUser());

// PUBLIC ROUTE FOR SHOWING ALL QUESTIONS
app.get("/", (req, res) => {
    Question.find()
      .sort({ date: "desc" })
      .then(questions => res.json(questions))
      .catch(err => res.json({ noquestions: "NO questions to display" }));
  });


//AUTH ROUTES
app.get("/register",function(req,res){
    res.render("register.ejs");
});

app.post("/register", function(req, res){
    var newUser = new User({username: req.body.username, email: req.body.email});
    User.register(newUser, req.body.password, function(err,user){
        if(err){
            console.log(err);
            return res.render("register")
        }
        passport.authenticate("local")(req,res, function(){
            res.redirect("/");
        })
    });
});

app.get("/login", function(req,res){
    res.render("login");
});

app.post("/login", passport.authenticate("local",
    {
        successRedirect: "/",
        failureRedirect: "/login"
    }), function(req,res){
});

// app.get("/testing",isLoggedIn,function(req,res){
//     res.send("Testing Page!!");
//     console.log("It worked!!");
// });

// PRIVATE ROUTE FOR SUBMITTING QUESTIONS
app.post("/", isLoggedIn, (req, res) => {
      const newQuestion = new Question({
        textone: req.body.textone,
        texttwo: req.body.texttwo,
        user: req.user.id,
        name: req.body.name
      });
      newQuestion
        .save()
        .then(question => res.json(question + "hi"))
        .catch(err => console.log("Unable to push question to database " + err));
    }
  );

// PRIVATE ROUTE FOR SUBMITTING ANSWERS TO QUESTIONS
app.post("/answers/:id", isLoggedIn, (req, res) => {
      Question.findById(req.params.id)
        .then(question => {
          const newAnswer = {
            user: req.user.id,
            name: req.body.name,
            text: req.body.text
          };
          question.answers.unshift(newAnswer);
  
          question
            .save()
            .then(question => res.json(question))
            .catch(err => console.log(err));
        })
        .catch(err => console.log(err));
    }
);

// MIDDLEWARE
function isLoggedIn(req,res,next){
    if(req.isAuthenticated()){
        return next();
    }
    res.redirect("/login");
}

app.listen(port, () => {
  console.log(`Server is listening on port ${port}`);
});