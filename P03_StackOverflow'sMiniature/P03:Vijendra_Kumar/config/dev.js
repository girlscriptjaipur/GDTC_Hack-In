const username = process.env.USERNAME;
const password = process.env.PASSWORD;

module.exports = {
	mongodbURI: "mongodb+srv://username:password@qabot-cluster-vzut8.mongodb.net/test?retryWrites=true&w=majority",
	secret: "mysecret"
	};
