const mongoose = require('mongoose');
const Schema = mongoose.Schema;

// Create User Schema
const PersonSchema = new Schema({
	name:{
		type: String,
		require: true
	},
	email:{
		type: String,
		required: true	
	},
	password:{
		type: String,
		require: true
	},
	username:{
		type: String
	},
	profilepic:{
		type: String,
		default: "https://cdn.imgbin.com/11/20/21/imgbin-computer-icons-user-profile-avatar-avatar-3ibtXvqfT3fcZQFVKBUKBL8Bb.jpg"
	},
	date:{
		type: Date,
		default: Date.now
	} 
});

module.exports = Person = mongoose.model('PersonSchema', PersonSchema);
