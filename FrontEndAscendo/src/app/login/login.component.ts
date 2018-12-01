import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import {Http,Response, Headers, RequestOptions } from '@angular/http';
import { Router, ActivatedRoute } from '@angular/router';  
import { LoginService } from '../service/login.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  msg: string = '';
  msgex: string = '';
  loginForm: FormGroup;
  submitted = false;
  //var listado login 
  listaLogin: {};

  constructor(private formBuilder: FormBuilder, private router: Router, private loginService: LoginService) { }
  //init 
  ngOnInit() {
    this.loginForm = this.formBuilder.group({
      email: ['', Validators.required],
      pass: ['', Validators.required]
    });
  }
  //returns controls form
  get f() { return this.loginForm.controls; }
  //function submit
  onSubmit() {
    this.submitted = true;
    // stop here if form is invalid
    if (this.loginForm.invalid) {
      return;
    } else {
      //send to login service
      this.loginService.Login(this.loginForm.value.email, this.loginForm.value.pass)
        .subscribe(data => {
          this.listaLogin = data;
          if (data[0] != undefined) {
             //save the id
             localStorage.setItem('idUser', data[0].id);
             //save the email
             localStorage.setItem('userEmail', data[0].email);
             //save the rol
             localStorage.setItem('userRol', data[0].rol);
            //redirect to home
            this.router.navigate(['/home'])
          } else {
            //msj error
            this.msg = "Usuario y/o contraseña no son validos";
          }
        });
    }
  }
  //function close msj
  closeAlert(): void {
    this.msg = '';
  }
}
