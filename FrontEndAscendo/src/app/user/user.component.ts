import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Http, Response, Headers, RequestOptions } from '@angular/http';
import { Router, ActivatedRoute } from '@angular/router';
import { UserService } from '../service/user.service';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {
  //var rol
  rol = '';
  //var id
  id = '';
  //var user
  user = '';
  msg: string = '';
  msgex: string = '';
  insertUserForm: FormGroup;
  submitted = false;
  //var listado user 
  listaUser: {};

  constructor(private formBuilder: FormBuilder, private router: Router, private userService: UserService) { }

  //init 
  ngOnInit() {
    //obtain the rol
    this.rol = localStorage.getItem("userRol");
    //obtain the email
    this.user = localStorage.getItem("userEmail");
    //obtain the id
    this.id = localStorage.getItem("idUser");
    //init form
    this.insertUserForm = this.formBuilder.group({
      valor: ['', Validators.compose([Validators.required, Validators.min(1000)])]
    });
  }
  //returns controls form
  get f() { return this.insertUserForm.controls; }
  //function submit
  onSubmit() {
    this.submitted = true;
    // stop here if form is invalid
    if (this.insertUserForm.invalid) {
      return;
    } else {
      //send to login service
      this.userService.InsertCajero(this.insertUserForm.value.valor, this.id)
        .subscribe(data => {
          this.listaUser = data;
          if (data.mensaje == "Success") {
            //msj error
            this.msgex = "Error";
          } else {
            //msj error
            this.msg = data.mensaje;
          }
        });
    }
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
  close() {
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

