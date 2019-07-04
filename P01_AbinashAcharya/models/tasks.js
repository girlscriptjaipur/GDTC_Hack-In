const mongoose=require('mongoose');
const TaskSchema=mongoose.Schema({
  title :{
    type : String,
    required : true
  }
});
const Task = module.exports = mongoose.model('Task',TaskSchema);