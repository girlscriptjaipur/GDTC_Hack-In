const mongoose = require("mongoose");
const Schema = mongoose.Schema;

// Create Question Schema
const QuestionSchema = new Schema({
  user: {
    type: Schema.Types.ObjectId,
    ref: "PersonSchema"
  },
  textone: {
    type: String,
    required: true
  },
  texttwo: {
    type: String,
    required: true
  },
  name: {
    type: String
  },
  upvotes: [
    {
      user: {
        type: Schema.Types.ObjectId,
        ref: "PersonSchema"
      }
    }
  ],
  answers: [
    {
      user: {
        type: Schema.Types.ObjectId,
        ref: "PersonSchema"
      },
      text: {
        type: String,
        required: true
      },
      name: {
        type: String
      },
      date: {
        type: Date,
        default: Date.now
      }
    }
  ],
  date: {
    type: Date,
    default: Date.now
  }
});

module.exports = Question = mongoose.model("QuestionSchema", QuestionSchema);
