var express=require('express');
var router = express.Router();
var mongoose=require('mongoose');
const Task = require('../models/tasks');
router.get("/tasks",function(req,res,next) {
    Task.find(function(err, tasks){
    if(err){
        res.send(err);
    }
    res.json(tasks);
});
});

router.post("/task",function(req,res,next){
    let newTask = new Task({
        title:req.body.title
       
      });
      
      newTask.save((err,task)=>{
      if(err){
        res.json({msg : 'failed to add  task'});
      }
      else{
        res.json({msg: 'Task successfully added'});
      }
      });

})
 router.delete('/task/:id',function(req , res, next){
     Task.remove({_id:req.params.id},function(err, result){
        if(err){
            res.json(err);
        }else{
        res.json(result);
        }
     })
 }
 )
 router.put('/task/:id',function(req,res){
   var task = req.body;
   var updTask= {};
   if(task.title){
       updTask.title=task.title;
   }
   if(!updTask){
     res.status(400);
     res.json({error:"Bad Data"});
   }else{
     Task.update({_id:req.params.id},updTask,{},function(err, result){
      if(err){
        res.json(err);
           
      }
      res.json(result);
   }
 )
  }
});

module.exports=router;