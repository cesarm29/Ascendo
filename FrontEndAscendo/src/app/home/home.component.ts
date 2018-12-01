import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  //var rol
  rol = '';
  //var user
  user = '';
  constructor(private router: Router) { }

  ngOnInit() {
    //obtain the rol
    this.rol = localStorage.getItem("userRol");
    //obtain the email
    this.user = localStorage.getItem("userEmail");
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
