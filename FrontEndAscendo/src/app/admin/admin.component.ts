import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import {Http,Response, Headers, RequestOptions } from '@angular/http';
import { Router, ActivatedRoute } from '@angular/router';  
import { AdminService } from '../service/admin.service';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit {
  //var rol
  rol = '';	
  //var user
  user = '';
  msg: string = '';
  msgex: string = '';
  insertAdminForm: FormGroup;
  submitted = false;
  //var listado user 
  listaAdmin: {};

  constructor(private formBuilder: FormBuilder, private router: Router, private adminService: AdminService) { }

  //init 
  ngOnInit() {
    //obtain the rol
    this.rol = localStorage.getItem("userRol");
    //obtain the email
    this.user = localStorage.getItem("userEmail");
    //init form
    this.insertAdminForm = this.formBuilder.group({
      denominacion: ['', Validators.compose([Validators.required, Validators.min(1000), Validators.max(100000)]) ],
      cantidad: ['', Validators.compose([Validators.required, Validators.min(1), Validators.max(100000)])]
    });
    //init function data cajero
    this.getDataCajero();
  }
  //returns controls form
  get f() { return this.insertAdminForm.controls; }
  //function submit
  onSubmit() {
    this.submitted = true;
    // stop here if form is invalid
    if (this.insertAdminForm.invalid) {
      return;
    } else {
      //send to cajero service
      this.adminService.InsertCajero(this.insertAdminForm.value.denominacion, this.insertAdminForm.value.cantidad)
        .subscribe(data => {
          this.listaAdmin = data;
          if (data.mensaje == "Guardado satisfactoriamente") {
            //msj error
            this.msgex = "Almacenamiento Exitoso";
            //init function data cajero
            this.getDataCajero();
          } else {
            //msj error
            this.msg = data.mensaje;
          }
        });
    }
  }
   //function get data cajero
   getDataCajero() {
      //send to admin service
      this.adminService.GetCajero()
        .subscribe(data => {
          this.listaAdmin = data;
          console.log(this.listaAdmin);
        });
    }
  
  //function close msj
  closeAlert(): void {
    this.msg = '';
  }
  //function close msj ext
  closeAlertEx(): void {
    this.msgex = '';
  }
  //close the app
  close(){
    // remove user id
   localStorage.removeItem('idUser');
   // remove user 
   localStorage.removeItem('userEmail');
   // remove rol 
   localStorage.removeItem('userRol');
    //redirect to the login
   this.router.navigate(['']);
}
  
}



