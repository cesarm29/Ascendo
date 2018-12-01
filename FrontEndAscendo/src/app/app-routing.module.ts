import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { AdminComponent } from './admin/admin.component'; 
import { UserComponent } from './user/user.component'; 

//const routes
const routes: Routes = [
		{
            path: '',
            component: LoginComponent,
        },
        {
            path: 'home',
            component: HomeComponent,
        },
        {
            path: 'register',
            component: RegisterComponent,
        },
        {
            path: 'admin',
            component: AdminComponent,
        },
        {
            path: 'user',
            component: UserComponent,
        }
        
    ];

    @NgModule({
        imports: [
            RouterModule.forRoot(routes)
        ],
        exports: [
            RouterModule
        ],
        declarations: []
    })
    export class AppRoutingModule { }
