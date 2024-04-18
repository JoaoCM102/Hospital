import { Component, OnInit } from '@angular/core';
import { FormBuilder , Validators} from '@angular/forms';
import { Router } from '@angular/router';
import { LoginService } from '../../../services/auth/login.service';
import { LoginRequest } from '../../../services/auth/registerRequest';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent implements OnInit{
  login=this.formBuilder.group({
    email:["",[Validators.required]],
    password:["" , Validators.required]
  })

  constructor(private formBuilder:FormBuilder, private router:Router, private loginService: LoginService){}

  get username(){
    return this.login.controls.email
  }

  get password(){
    return this.login.controls.password
  }

  ngOnInit(): void {}

  loginAction(){
    this.loginService.logout();
    if (this.login.valid) {
      this.loginService.login(this.login.value as LoginRequest).subscribe({
        next: (userData) =>{
          console.log(userData)
        },
        error:(userData) => {
            console.log(userData)
            this.router.navigateByUrl("/inicioSesion")
            alert("Error al iniciar sesion")
        },
        complete:()=> {
            console.info("login completo ")
            console.info(this.loginService.currentUserLogin.value)
            this.router.navigateByUrl("inicio");
           this.login.reset();
        }
      });
    }else{
      this.login.markAllAsTouched();
      alert("Error al loguear");
    }
  }
}
