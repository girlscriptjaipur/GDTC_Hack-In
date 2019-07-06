const express = require('express');
//const mongoose = require('mongoose');
const passport = require('passport');

// Import Person Model 
const Person = require('../../models/Person');

// Import Profile Model
const Profile = require('../../models/Profile');

// Make routes to server/app
const router = express.Router();

// ----------------------- { Test for API endpoints }-----------------------
// @route   GET api/profile/test
// @desc    Tests profile route
// @access  PUBLIC
router.get("/test", (req, res) => res.json({ test: "Profile is being tested..."}));

//---------------------- { current user private profile API endpoint }----------
// @route   GET api/profile
// @desc    Get current users/person's profile
// @access  PRIVATE
router.get('/', passport.authenticate('jwt', { session: true }), (req, res) => {
        Profile.findOne({ user: req.user.id })
            .then(profile => {
                if(!profile){
                    return res.status(404).json({ profilenotfound: "No profile found"
                    });
                }
                res.json(profile);
            })
            .catch(err => console.log("Got some error in profile" + err));
});

//---------------------- { user profile updation API endpoint }--------------
// @type/Method    POST
//@route           /api/profile/
// @desc           route for UPDATING/SAVING personal user profile
// @access         PRIVATE
router.post('/', passport.authenticate('jwt', { session: false}), (req, res) => {
        // Get profile fields/Values
        const profileValues = {};
        profileValues.user = req.user.id;
        if(req.boby.username) profileValues.username = req.body.username;
        if(req.body.website) profileValues.website = req.body.website;
        if(req.body.country) profileValues.country = req.body.country;
        if(req.body.portfolio) profileValues.portfolio = req.body.portfolio;
        
        // Languages - Spilt into array
        if(typeof req.body.languages !== undefined){
            profileValues.languages = req.body.languages.split(",");
        }
        
        // Social links (optional fields/Vlaues)
        profileValues.country.social = {};
        if(req.body.youtube) profileValues.social.youtube = req.body.youtube;
        if(req.body.facebook) profileValues.social.facebook = req.body.facebook;
        if(req.body.instagram) profileValues.social.instagram = req.body.instagram;
        
        // Database Checkpoints
        // Create or Edit current user profile with unique handle/user_id
        Profile.findOne({ user: req.user.id })
            .then(profile => {
                if (profile) {
                    // If profile not exist, then create a new one, Otherwise just update 

                    Profile.findOneAndUpdate(
                        { user: req.user.id },
                        { $set: profileValues },
                        { new: true }
                    )
                    .then(profile => res.json(profile))
                    .catch(err => console.log("problem in Updation" + err));
                } else {
                    Profile.findOne({ username: profileValues.username })
                        .then(profile => {
                            // Check whether handle/username already exists in DB
                            if(profile) {
                                res.sendStatus(400).json({ username: "Username already exists "});
                            }
                            // Save User details to DB
                            new Profile(profileValues)
                                .save()
                                .then(profile => res.json(profile))
                                .catch(err => console.log(err));
                        })
                        .catch(err => console.log(err));
                }
            })
            .catch(err => console.log("Problem occuring to fetching profile" + err));
});

//---------------------- { user's username public access API endpoint }--------------
// @type/Method     GET
//@route            /api/profile/:username
// @desc            route for getting user profile based on USERNAME
// @access          PUBLIC
router.get('/:username', (req, res) => {
    Profile.findOne({ username: req.params.username })
        .populate("user", ["name", "profilepic"])
        .then(profile => {
            if (!profile) {
                res.status(404).json({ userNotFound: "User not found!!!" });
            }
            res.json(profile);
        })
        .catch(err => console.log("Error in the process of username fetching!!!" + err));
});

//---------------------- { To search all user's profile, API endpoint }--------------
// @type/Method         GET
//@route                /api/profile/find/everyone
// @desc                route for getting user profile of all_users
// @access              PUBLIC
router.get('/find/everyone', (req, res) => {
    Profile.find()
        .populate("user", ["name", "profilepic"])
        .then(profiles => {
            if (!profiles) {
                res.status(404).json({ userNotFound: "There are no pofiles!!!"});
            }
            res.json(profiles);
        })
        .catch(err => console.log("There are no pofiles!!!" + err));
});

//------------------ { To delete user profile privetely, API endpoint }--------------
// @type/Method        DELETE
//@route               /api/profile/
// @desc               route for deleting user based on ID
// @access             PRIVATE
router.delete('/', passport.authenticate('jwt', {session: false }), (req, res) => {
        Profile.findOne({ user: req.user.id });
        Profile.findAndremove({ _id: req.user.id })     
        .then(() => {
                Person.findOneAndRemove({ _id: req.user.id })
                    .then(() => res.json({ sucess: "sucessfully deleted..."}))
                    .catch(err => console.log(err));

        })
        .catch(err => console.log(err));
});

//---------------------- { user workrole private API endpoint }--------------
// @type/Methos         POST
//@route                /api/profile/workrole
// @desc                route for adding work profile of a person
// @access              PRIVATE
router.post('/workrole', passport.authenticate('jwt', { session: true }), (req, res) => {
        Profile.findOne({ user: req.user.id })
            .then(profile => {
                const newWork = {
                    role: req.body.role,
                    company: req.body.company,
                    country: req.body.country,
                    from: req.body.from,
                    to: req.body.to,
                    current: req.body.current,
                    details: req.body.details
                };
                
                profile.workrole.unshift(newWork);
                profile
                    .save()
                    .then(profile => res.json(profile))
                    .catch(err => console.log(err));
            })
            .catch(err => console.log(err));
});

//------------------- { To delete user's workrole privetely, API endpoint }-------------
// @type/Method         DELETE
//@route                /api/profile/workrole/:w_id
// @desc                route for deleting a specific workrole
// @access              PRIVATE
router.delete('/workrole/:w_id', passport.authenticate('jwt', { session: false }), (req, res) => {
        Profile.findOne({ user: req.user.id })
            .then(profile => {
                //Check, if we got a profile
                const removethis = profile.workrole
                    .map(item => item.id)
                    .indexOf(req.params.w_id);

                    profile.workrole.splice(removethis, 1);

                    profile
                        .save()
                        .then(profile => res.json(profile))
                        .catch(err => console.log(err));
            })
            .catch(err => console.log(err));
});

module.exports = router;
