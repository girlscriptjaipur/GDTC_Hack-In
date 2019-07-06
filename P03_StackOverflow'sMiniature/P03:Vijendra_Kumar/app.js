// Userful module Imports first
const express = require('express');
const mongoose = require('mongoose');
const bodyparser = require('body-parser');
const passport = require('passport'); 

// Import all routes
const auth = require('./routes/api/auth');
const profile = require('./routes/api/profile');
const questions = require('./routes/api/questions');

const app = express();

//Middleware for bodyparser
app.use(bodyparser.urlencoded({ extended: false}));
app.use(bodyparser.json());     //Now, We are in APIs world

// DB Config
const db = require('./config/dev').mongodbURI;

// Connect to MongoDB 
mongoose
  .connect(db, { useNewUrlParser: true }) // Let us remove that nasty deprecation warrning :)
  .then(() => console.log('MongoDB Connected...'))
  .catch(err => console.log(err));

// passport middleware
app.use(passport.initialize());

//Configuration for passport JWT strategy
require("./strategies/jsonwtStrategy")(passport);

//------------------------------------
//----- APIs (shiped to route/apis) ---
//For testing purpose only -> route
app.get("/", (req, res) => {
	// console.log("Welcome to Qabot");
	res.status(200).end("Welcome to QaBot...");
}); 

// Use routes for access to API endpoints
app.use('/api/auth', auth);
app.use('/api/profile', profile);
app.use('/api/questions', questions);

// Server setup
const port = process.env.PORT || 3000;

app.listen(port, () => console.log(`Server/App is running on port ${port}`));

