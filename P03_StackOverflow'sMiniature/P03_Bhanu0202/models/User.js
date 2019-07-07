var mongoose = require("mongoose");
const Schema = mongoose.Schema;

const UserSchema = new Schema({
	username: {
		type: String,
		require: true
	},
	email: {
		type: String,
		require: true
	},
	password : {
		type: String,
		require: true
	},
	profilepic: {
    	type: String,
    	default: "https://learncodeonline.in/manicon.png"
    },
    date: {
    	type: Date,
    	default: Date.now
    }
});

module.exports = User = mongoose.model("UserSchema", UserSchema);