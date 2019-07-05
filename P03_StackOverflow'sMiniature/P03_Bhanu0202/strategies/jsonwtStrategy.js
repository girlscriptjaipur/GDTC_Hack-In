const JwtStrategy = require("passport-jwt").Strategy;
const ExtractJwt = require("passport-jwt").ExtractJwt;
var key = require("../setup/myurl.js");

var opts = {};
opts.jwtFromRequest = ExtractJwt.fromAuthHeaderAsBearerToken();
opts.secretOrKey = key.secret;

var Strategy = new JwtStrategy(opts, (jwt_payload, done) => {
	User.findById(jwt_payload.id)
		.then(person => {
			if(person)
				return done(null, person);
			else
				return done(null, false);
		})
		.catch(err => {
			res.send("error is", err.message);
		});
})

module.exports = passport => {passport.use(Strategy);};