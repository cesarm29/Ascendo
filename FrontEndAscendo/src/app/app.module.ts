//libs generic
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { HttpModule } from '@angular/http';
//router
import { AppRoutingModule } from './app-routing.module';
//components
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { HomeComponent } from './home/home.component';
import { AdminComponent } from './admin/admin.component';
import { UserComponent } from './user/user.component';
//services
import { LoginService } from './service/login.service';
import { RegisterService } from './service/register.service';
import { UserService } from './service/user.service';
import { AdminService } from './service/admin.service';


@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterComponent,
    HomeComponent,
    AdminComponent,
    UserComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpModule,
    HttpClientModule,
    ReactiveFormsModule
  ],
  providers: [LoginService,RegisterService,UserService,AdminService],
  bootstrap: [AppComponent]
})
export class AppModule { }
