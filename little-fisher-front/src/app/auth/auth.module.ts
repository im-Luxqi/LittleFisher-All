import { LogoutComponent } from './logout/logout.component';
import { ResetPasswordComponent } from './reset-password/resetPassword.component';
import { RequestPasswordComponent } from './request-password/requestPassword.component';
import { RegisterComponent } from './register/register.component';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';

import { NbAlertModule, NbButtonModule, NbCheckboxModule, NbInputModule } from '@nebular/theme';


import { AuthRoutingModule } from './auth-routing.module';
import { LoginComponent } from './login/login.component';
import { NgModule } from '@angular/core';
import { NbAuthModule } from '@nebular/auth';


const AUTH_COMPONENTS = [
  LogoutComponent,
  LoginComponent,
  RegisterComponent,
  RequestPasswordComponent,
  ResetPasswordComponent,
]

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    RouterModule,
    NbAlertModule,
    NbInputModule,
    NbButtonModule,
    NbCheckboxModule,


    AuthRoutingModule,
    NbAuthModule,
  ], 
  declarations: [
    ...AUTH_COMPONENTS,
  ],
})
export class AuthModule {
}
