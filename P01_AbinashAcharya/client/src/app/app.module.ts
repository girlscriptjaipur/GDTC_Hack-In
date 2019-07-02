import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { TaskComponent } from './task/task.component';
import { HttpModule } from '@angular/http';
import {FormsModule} from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';
import {MessageService}  from './message.service';
import {HttpErrorHandlerService} from './http-error-handler.service';

@NgModule({
  declarations: [
    AppComponent,
    TaskComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpModule,
    FormsModule
  ],
  providers: [MessageService,HttpErrorHandlerService],
  bootstrap: [AppComponent]
})
export class AppModule { }
