import { Injectable } from '@angular/core';
import {Http,Response, Headers, RequestOptions } from '@angular/http';
import { Observable } from 'rxjs/Observable';   
import 'rxjs/add/operator/map';  
import 'rxjs/add/operator/do'; 

@Injectable()
export class LoginService {
  //init constructor
  constructor(private http: Http) { }
  //init params
  params;
   //service to postgresql login
   Login(email, pass){
     //body parameters to json 
    var body = {
      "email": email,
      "password": pass
      }; 
     let headers = new Headers({ 'Content-Type': 'application/json' });
     let options = new RequestOptions({ headers: headers });  
     return this.http.post('http://localhost:8081/ascendoApi/api/usuario/loginUsuario', body, options).map((response: Response) =>response.json())              
   }
}
