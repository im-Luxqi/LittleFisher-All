import { Component, OnInit } from "@angular/core";
import { NbLoginComponent, NbAuthService, NbAuthJWTToken } from "@nebular/auth";


@Component({
  selector: "ngx-login",
  templateUrl: "./login.component.html"
})
export class LoginComponent extends NbLoginComponent {

  loginSubscription:any;

  ngOnInit() {
    this.loginSubscription= this.service.onTokenChange().subscribe((token: NbAuthJWTToken) => {
        if (token.isValid()) {
          console.log(token.getPayload()["username"] + "------------------------拿到");      
          this.router.navigate(['pages/dashboard']); 
        }
      });
  }
  ngOnDestroy(){
    this.loginSubscription.unsubscribe(); 
  }
  
}
