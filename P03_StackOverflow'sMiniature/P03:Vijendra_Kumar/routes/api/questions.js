const express = require("express");
const mongoose = require("mongoose");
const passport = require("passport");

//Import Person Model
const Person = require("../../models/Person");

//Import Profile Model
const Profile = require("../../models/Profile");

//Import Question Model
const Question = require("../../models/Question");

// Make routes to server/app
const router = express.Router();

// ---------------------------------------- { Questions API endpoints }-------------------
// @type/Method         GET
//@route                /api/questions/
// @desc                route to get all the questions 
// @access              PUBLIC
router.get('/', (req, res) => {
    Question.find()
        .sort({ date: "desc" })
        .then(questions => res.json(questions))
        .catch(err => res.json({ notifications: "there is no any question to display!!!" }));
});

// @type/Method         POST
//@route                /api/questions/
// @desc                route for submitting questions
// @access              PRIVATE
router.post('/', passport.authenticate('jwt', { session: false }), (req, res) => {
        const newQuestion = new Question({
            textone: req.body.textone,
            texttwo: req.body.texttwo,
            user: req.user.id,
            name: req.body.name
        });
        newQuestion
            .save()
            .then(question => res.json(question))
            .catch(err => console.log("Unable to push question to the DB!!!" + err));
});

// ---------------------------------------- { Answers API endpoints }-------------------
// @type/Method         POST
//@route                /api/questions/answers/:id
// @desc                private route for submitting answers to an existing question
// @access              PRIVATE
router.post('/answers/:id', passport.authenticate("jwt", { session: false }), (req, res) => {
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
});

// ---------------------------------------- { Upvotes API endpoints }-------------------
// @type/Method         POST
//@route                /api/questions/upvote/:id
// @desc                route to increase upvote array 
// @access              PRIVATE
router.post('/upvote/:id', passport.authenticate('jwt', { session: false }), (req, res) => {
        Profile.findOne({ user: req.user.id })
            .then(profile => {
                Question.findById(req.params.id)
                    .then(question => {
                        if (question.upvotes.filter(upvote => upvote.user.toString() === req.user.id.id.toString()).length > 0)
                        {
                            return res.status(400).json({ noupvote: "User already upvoted!!!"});
                        }
                        question.upvotes.unshift({ user: req.user.id });
                        question
                            .save()
                            .then(question => releaseEvents.json(question))
                            .catch(err => console.log(err));
                    })
                    .catch(err => console.log(err));
            })
            .catch(err => console.log(err));
});


module.exports = router;
