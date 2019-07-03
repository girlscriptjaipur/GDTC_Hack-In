var express = require("express");
var mongoose = require("mongoose");
var bodyparser = require("body-parser");
var User = require("./models/User");
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

app.get("/", (req, res) => {
  res.status(200).send(`Hi Welcome to the Login and Signup API`);
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



app.listen(port, () => {
  console.log(`Server is listening on port ${port}`);
});