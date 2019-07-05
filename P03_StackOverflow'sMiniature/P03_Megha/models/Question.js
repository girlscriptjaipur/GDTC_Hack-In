const mongoose = require("mongoose");
const Schema = mongoose.Schema;

const QuestionSchema = new Schema({
    name: {
        type: String,
        required: true
    },
    text: {
        type: String,
        required: true
    },
    user:{
        type:Schema.Types.ObjectId,
        ref:"User"
    },
    

    answers: [
        {
            name: {
                type: String,
                required: true
            },
            text: {
                type: String,
                required: true
            }
            

        }
    ]


});
module.exports = Question = mongoose.model("Question", QuestionSchema);
