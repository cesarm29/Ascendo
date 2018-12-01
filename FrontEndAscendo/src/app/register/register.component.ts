import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import {Http,Response, Headers, RequestOptions } from '@angular/http';
import { Router, ActivatedRoute } from '@angular/router';  
import { RegisterService } from '../service/register.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  msg: string = '';
  msgex: string = '';
  registerForm: FormGroup;
  submitted = false;
  //var listado register 
  listaRegister: {};
  //constructor
  constructor(private formBuilder: FormBuilder, private router: Router, private registerService: RegisterService) { }

  //init 
  ngOnInit() {
    this.registerForm = this.formBuilder.group({
      nombres: ['', Validators.required],
      email: ['', Validators.required],
      pass: ['', Validators.required],
      telefono: ['', Validators.required],
      direccion: ['', Validators.required],
      rol: ['', Validators.required]
    });
  }

  //returns controls form
  get f() { return this.registerForm.controls; }
  //function submit
  onSubmit() {
    this.submitted = true;
    // stop here if form is invalid
    if (this.registerForm.invalid) {
      return;
    } else {
      //send to register service
      this.registerService.Register(this.registerForm.value.nombres,this.registerForm.value.email, this.registerForm.value.pass,this.registerForm.value.telefono,this.registerForm.value.direccion,this.registerForm.value.rol)
        .subscribe(data => {
          this.listaRegister = data;
          console.log(data);
          if (data.mensaje == "Guardado satisfactoriamente") {
            //msj sucess
            this.msgex = "Usuario Creado Exitosamente!!";
            //redirect to home
            this.router.navigate(['/'])
          } else {
            //msj error
            this.msg = "Error el usuario no se pudo crear";
          }
        });
    }
  }
  //function close msj
  closeAlert(): void {
    this.msg = '';
  }



}
