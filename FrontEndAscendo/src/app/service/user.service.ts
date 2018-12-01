import { Injectable } from '@angular/core';
import {Http,Response, Headers, RequestOptions } from '@angular/http';
import { Observable } from 'rxjs/Observable';   
import 'rxjs/add/operator/map';  
import 'rxjs/add/operator/do'; 

@Injectable()
export class UserService {

  //init constructor
  constructor(private http: Http) { }
  //init params
  params;
   //service to postgresql user process data cajero 
   InsertCajero(valor, id){ 
     //body parameters to json 
    var body = {
      "valor": valor,
      "idUsuario": id
      };
      let headers = new Headers({ 'Content-Type': 'application/json' });
      let options = new RequestOptions({ headers: headers }) 
     return this.http.post('http://localhost:8081/ascendoApi/api/cajero/retiroDineroCajero', body, options).map((response: Response) =>response.json())              
   }

}
