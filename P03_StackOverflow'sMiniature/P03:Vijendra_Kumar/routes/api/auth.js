const express = require('express');
const bcrypt =  require('bcryptjs');
const jsonwt = require('jsonwebtoken');
const passport = require('passport');  //To be needed for profile auth(Very Useful for user authentication)
const key = require('../../config/dev'); 

// Import Person Model
const Person = require('../../models/Person');

// Make routes to server/app
const router = express.Router();

// ----------------------- { Test for API endpoints }--------------------------
// @type/Method    GET
// @route          /api/auth/test
// @desc           just for testing
// @access         PUBLIC
router.get("/test", (req, res) => res.json({ test: "Auth is being tested..."}));

//---------------------- { user Registeration API endpoint }--------------
// @type/Method    POST
// @route          /api/auth/register
// @desc           person/user registeration route
// @access         PUBLIC
router.post("/register", (req, res) => {
    Person.findOne({ email: req.body.email })
      .then(person => {
        if (person) {
          return res
            .status(400)
            .json({ emailerror: "Email is already registered!!!" });
        } else {
          const newPerson = new Person({
            name: req.body.name,
            email: req.body.email,
            password: req.body.password
          });
          //Encrypt password with bcrypt
          bcrypt.genSalt(10, (err, salt) => {
            bcrypt.hash(newPerson.password, salt, (err, hash) => {
              if (err) throw err;
              newPerson.password = hash;
              newPerson
                .save()
                .then(person => res.json(person))
                .catch(err => console.log(err));
            });
          });
        }
      })
      .catch(err => console.log(err));
});

//---------------------- { user Login API endpoint }------------------------
// @type/Method     POST
//@route            /api/auth/login
// @desc            Login User/Returning JWT Token
// @access          PUBLIC
router.post("/login", (req, res) => {
    const email = req.body.email;
    const password = req.body.password;

    //Check in DB and authentice, user/person by email
    Person.findOne({ email })
        .then(person => {
            if(!person){
                return res
                    .status(404)
                    .json({ emailerror: "User not found with this email!!!"});
            }
            // Check Password
            bcrypt
                .compare(password, person.password).then(isCorrect => {
                    if(isCorrect){
                        // User Matched
                        //res.json({ success: "User is able to login successfully" });
                        //use payload and create token for user(Create JWT Payload)
                        const payload = {
                            id: person.id,
                            name: person.name,
                            email: person.email
                        };
                         // Sign Token
                        jsonwt.sign(payload, key.secret, { expiresIn: 3600 }, (err, token) => {
                            res.json({
                                success: true,
                                token: "Bearer " + token
                            });
                        });
                    } else {
                        res.status(400).json({ passworderror: "Password is not correct!!!"});
                    }
                })
                .catch(err => console.log(err));
        })
        .catch(err => console.log(err));
});

//---------------------- { user private view Profile API endpoint }--------------
// @type/Method     GET
//@route            /api/auth/profile
// @desc            Return current user's profile(route for user profile)
// @access          PRIVATE
router.get('/profile', passport.authenticate('jwt',{ session: false }), (req, res) => {
        res.json({
            id: req.user.id,
            name: req.user.email,
            email: req.user.email,
            profilepic: req.user.profilepic
        });
    });

module.exports = router;