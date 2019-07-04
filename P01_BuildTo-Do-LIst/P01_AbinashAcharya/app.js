var express = require('express');
var path = require('path');
var mongoose=require('mongoose');
var bodyParser=require('body-parser');
var cors=require('cors');
var tasks = require('./routes/tasks');

mongoose.connect('mongodb://localhost:27017/tasks'); //connecting to mongdodb

mongoose.connection.on('connected',()=>{
    console.log('Connected to database @ 27107');
  })
  
  mongoose.connection.on('error',(err)=>{
    if(err){
    console.log('Error in database connection: ' + err);
  }
  })
const port=3000;
var app=express();
app.use(cors());
app.use(bodyParser.json());

app.use('/api',tasks);
app.listen(port,function(){
console.log('Server started on port ' + port);
});