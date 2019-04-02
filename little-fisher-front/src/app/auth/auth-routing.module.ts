import { LogoutComponent } from './logout/logout.component';
import { RouterModule, Routes } from '@angular/router';
import { NgModule } from '@angular/core';

import {LoginComponent} from './login/login.component';
import {NbAuthComponent} from '@nebular/auth';
import { RegisterComponent } from './register/register.component';
import { RequestPasswordComponent } from './request-password/requestPassword.component';
import { ResetPasswordComponent } from './reset-password/resetPassword.component';

const routes: Routes = [{
  path: '',
  component: NbAuthComponent,
  children: [
    {
      path: 'login',
      component: LoginComponent,
    },
    {
      path: 'register',
      component: RegisterComponent,
    },
    {
      path: 'logout',
      component: LogoutComponent,
    },
    {
      path: 'request-password',
      component: RequestPasswordComponent,
    },
    {
      path: 'reset-password',
      component: ResetPasswordComponent,
    },
    { path: '', redirectTo: 'login', pathMatch: 'full' },
    { path: '**', redirectTo: 'login' },
  ]
}];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class AuthRoutingModule {
}
