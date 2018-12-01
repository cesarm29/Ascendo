import { Injectable } from '@angular/core';
import {Http,Response, Headers, RequestOptions } from '@angular/http';
import { Observable } from 'rxjs/Observable';   
import 'rxjs/add/operator/map';  
import 'rxjs/add/operator/do'; 

@Injectable()
export class AdminService {

  //init constructor
  constructor(private http: Http) { }
  //init params
  params;
   //service to postgresql admin insert
   InsertCajero(denominacion, cantidad){
    //body parameters to json 
    var body = {
      "denominacion": denominacion,
      "cantidad": cantidad
      };
     let headers = new Headers({ 'Content-Type': 'application/json' });
     let options = new RequestOptions({ headers: headers });  
     return this.http.post('http://localhost:8081/ascendoApi/api/cajero/guardarDineroCajero', body, options).map((response: Response) =>response.json())              
   }
   //service to postgresql admin get data cajero
   GetCajero(){
     //all data
     let headers = new Headers({ 'Content-Type': 'application/x-www-form-urlencoded' });
     let options = new RequestOptions({ headers: headers });  
     return this.http.post('http://localhost:8081/ascendoApi/api/cajero/consultaDineroCajero', options).map((response: Response) =>response.json())              
   }

}
