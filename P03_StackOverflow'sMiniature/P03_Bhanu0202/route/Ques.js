var express = require('express');
var route = express.Router();
var key = require("../setup/myurl");
const jsonwt = require("jsonwebtoken");
var passport = require("passport");
require("../strategies/jsonwtStrategy") (passport);
var bcrypt = require("bcrypt");
var saltRounds = 10;

//Models
var User = require("../models/User");
var Profile = require("../models/Profile");
var Ques = require("../models/Ques");

route.get("/", (req, res) => {
  Ques.find()
    .sort({ date: "desc" })
    .then(questions => res.json(questions))
    .catch(err => res.json({ noquestions: "NO questions to display" }));
});

route.post("/", passport.authenticate("jwt", { session: false }),
	(req, res) => {
		const newQues = new Ques({
	      header: req.body.header,
	      description: req.body.description,
	      user: req.user.id,
	      name: req.body.name
	    });
	    newQues
	      .save()
	      .then(ques => res.json(ques))
	      .catch(err => console.log("Error is " + err));
});

route.post("/answer:id", passport.authenticate("jwt", { session: false }),
	(req, res) => {
		Ques.findById(req.params.id)
	      .then(ques => {
	        const newAns = {
	          user: req.user.id,
	          name: req.body.name,
	          text: req.body.text
	        };
	        ques.answers.unshift(newAns);

	        ques
	          .save()
	          .then(ques => res.json(ques))
	          .catch(err => console.log("Error is " + err));
	      })
	      .catch(err => console.log("Error is " + err));
});

route.post("/upvote:id", passport.authenticate("jwt", { session: false }),
	(req, res) => {
		Ques.findById(req.params.id)
	      .then(ques => {
	      	if (
              ques.upvotes.filter(
                upvote => upvote.user.toString() === req.user.id.toString()
              ).length > 0
	            ) {
	              return res.status(400).json({ noupvote: "User already upvoted" });
	            }
	            ques.upvotes.unshift({ user: req.user.id });
	            ques
	              .save()
	              .then(ques => res.json(ques))
	              .catch(err => console.log("Error is " + err));
	          })
	          .catch(err => console.log("Error is " + err));
	});


module.exports = route;