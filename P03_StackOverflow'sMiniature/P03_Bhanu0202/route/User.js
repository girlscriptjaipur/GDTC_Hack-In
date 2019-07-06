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

route.get('/', (req, res) => {
	res.status(200).send(`Welcome to login and signup API`);
});

route.post('/signup', async (req, res) => {
	var newUser = new User({
		username: req.body.username,
		email: req.body.email,
		password: req.body.password
	});
	await User.findOne({email: newUser.email})
		.then(async profile => {
			if(!profile){
				bcrypt.hash(req.body.password, saltRounds, async (err, hash) => {
					if(err)
						res.send("Error is", err.message);
					else{
						newUser.password = hash;
						await newUser
							.save()
							.then(() => {
								res.status(200).send(newUser);
							})
							.catch(err => {
								console.log("error is", err.message);
							});
					}
				})
				
			} else {
				res.send("user already exists");
			}
		})
	
});

route.post('/login', async (req, res) => {
	var newUser = {};
	newUser.username = req.body.username;
	newUser.email = req.body.email;
	newUser.password = req.body.password;

	await User.findOne({username: newUser.username,
						email: newUser.email})
		.then(profile => {
			if(!profile)
				res.send("user not find");
			else{
				bcrypt.compare(newUser.password, profile.password, (err, result) => {
					if(err)
						console.log("error is", err.message);
					else if(result){
						const payload = {
							id: profile.id,
							username: profile.username
						}
						jsonwt.sign(
							payload,
							key.secret,
							{expiresIn: 3600},
							(err, token) => {
								res.json({
									success: true,
									token: "Bearer " + token
								});
							}
							);
						//res.send("user authenticated");
					}
					else
						res.send("User unauthorised access");
				})
			}
		});
});

route.get(
	"/profile",
	passport.authenticate("jwt", {session: false}),
	(req, res) => {
		console.log(req);
		res.json({
			id: req.user.id,
			username: req.user.username,
			email: req.user.email,
      		profilepic: req.user.profilepic
		});
	}
	);

module.exports = route;