import { Injectable } from '@angular/core';
import {Http} from '@angular/http';
import {HttpErrorResponse} from '@angular/common/http';
import {MessageService} from './message.service';
import {Observable,of} from 'rxjs';

export type HandleError = <T>(
  operation?: string,
  result?:T
) => (error : HttpErrorResponse) => Observable<T>;
@Injectable({
  providedIn: 'root'
})
export class HttpErrorHandlerService {

  constructor(private messageService:MessageService) { }

  createHandleError = (serviceName = "") => <T>(
    operation = 'operation',
    result = {} as T
  ) => this.handleError(serviceName,operation,result);

  handleError<T>(serviceName="",operation='operation',result={} as T){
    return (error : HttpErrorResponse) : Observable<T> =>{
      console.error(error);

      const message= error.error instanceof ErrorEvent ? error.error.message : `server returned code ${error.status} with body "${error.error}"`;
      this.messageService.add(
        `${serviceName} : ${operation} failed ${message}`

      );
      return of(result);
    };
  }



  
}
