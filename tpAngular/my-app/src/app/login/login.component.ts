import { Component, OnInit } from '@angular/core';
import { LoginRequest } from '../common/data/login_request';
import { LoginResponse } from '../common/data/login_response';
import { LoginService } from '../common/service/login.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginRequest  = new LoginRequest();
  message = "";

  constructor(private loginService : LoginService) { }

  doLogin(){
    this.loginService.postLogin$(this.loginRequest)
    .subscribe({
       next: (loginResponse:LoginResponse)=>{ 
                this.postTraitementLoginResponse(loginResponse);
              },
       error: (err)=>{ sessionStorage.setItem("token" , "null" ); console.log(err);
                      this.message="echec login" }
    });
  }

  postTraitementLoginResponse(loginResponse:LoginResponse){
      sessionStorage.setItem("token" , loginResponse.token );
      this.message = loginResponse.message;
      console.log(JSON.stringify(loginResponse));
  }

  ngOnInit(): void {
  }

}
