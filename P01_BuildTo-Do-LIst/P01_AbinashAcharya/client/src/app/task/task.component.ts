import { Component, OnInit } from '@angular/core';
import {Task} from '../taskdef';
import {TasksService} from '../tasks.service';

@Component({
  selector: 'app-task',
  templateUrl: './task.component.html',
  styleUrls: ['./task.component.css']
})
export class TaskComponent implements OnInit {
  tasks : Task[];
  editTask : Task;
  constructor(private taskService : TasksService) { }

  ngOnInit() {
    this.taskService.getTasks().subscribe(tasks => (this.tasks=tasks));
  }
 

  getTasks():void{
    this.taskService.getTasks().subscribe(tasks => (this.tasks=tasks));
  }
 add(title : string) : void{
  this.editTask= undefined;
  title=title.trim();
  if(!title){
    return;
  }

  const newTask : Task = {title} as Task;
  this.taskService.addTask(newTask).subscribe(task=>(this.tasks.push(task)))
  this.taskService.getTasks().subscribe(tasks => (this.tasks=tasks));
 }
 delete(task :Task) : void{
   this.tasks = this.tasks.filter(h => h!==task);
   this.taskService.deleteTask(task._id).subscribe();
   this.getTasks();
 }

 edit(task){
   this.editTask=task;
 }
 update(){
   if(this.editTask){
     this.taskService.updateTask(this.editTask).subscribe(task => {
       const x = task ? this.tasks.findIndex(h => h._id===task._id) : -1;
       if (x > -1){
         this.tasks[x]=task;
       }
     });
        this.editTask=undefined;
   }
   this.getTasks();
 }
}
