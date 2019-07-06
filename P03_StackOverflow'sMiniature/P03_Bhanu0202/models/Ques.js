var mongoose = require("mongoose");
const Schema = mongoose.Schema;

const QuesSchema = new Schema({
  user: {
    type: mongoose.Types.ObjectId,
    ref: "myPerson"
  },
  header: {
    type: String,
    required: true
  },
  description: {
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
        ref: "myPerson"
      }
    }
  ],
  answers: [
    {
      user: {
        type: Schema.Types.ObjectId,
        ref: "myPerson"
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

module.exports = Ques = mongoose.model("QuesSchema", QuesSchema);
