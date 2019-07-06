
var express = require("express");
var mongoose = require("mongoose");
var bodyparser = require("body-parser");
var key = require("./setup/myurl");
const jsonwt = require("jsonwebtoken");
var passport = require("passport");
require("./strategies/jsonwtStrategy") (passport);
const user = require("./route/User");
const ques = require("./route/Ques");
const profile = require("./route/Profile");

var app = express();

const hostname = '127.0.0.1';
const port =process.env.PORT || 3000;

app.use(bodyparser.urlencoded({ extended: false }));
app.use(bodyparser.json());
app.use(passport.initialize());

mongoose
	.connect(key.myurl)
	.then(() => {
		console.log("Database is connected");
	})
	.catch(err => {
		console.log("error is ", err.message);
	});

app.use("/api/user", user);
app.use("/api/profile", profile);
app.use("/api/questions", ques);

app.listen(port, hostname, () => {
	console.log(`Server is running on ${hostname}:${port}`);
});

