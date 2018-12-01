import { Injectable } from '@angular/core';
import {Http,Response, Headers, RequestOptions } from '@angular/http';
import { Observable } from 'rxjs/Observable';   
import 'rxjs/add/operator/map';  
import 'rxjs/add/operator/do'; 

@Injectable()
export class RegisterService {
  //init constructor
  constructor(private http: Http) { }
  //init params
  params;
   //service to postgresql register user
   Register(nombres,email,pass,telefono,direccion,rol){ 
    //body parameters to json 
    var body = {
      "nombres": nombres,
      "email": email,
      "password": pass,
      "telefono": telefono,
      "direccion": direccion,
      "rol": rol
      };
     let headers = new Headers({ 'Content-Type': 'application/json' });
     let options = new RequestOptions({ headers: headers });  
     return this.http.post('http://localhost:8081/ascendoApi/api/usuario/guardarUsuario', body, options)
     .map((response: Response) =>response.json())              
   }
}
