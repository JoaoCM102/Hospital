import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Direccion, LoginRequest } from '../../../services/auth/registerRequest';
import { RegisterService } from '../../../services/auth/register.service';
import { LoginService } from '../../../services/auth/login.service';
@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrl: './register.component.css'
})


export class RegisterComponent implements OnInit {
    grupo=  this.formBuilder.group({
    municipio: ["123456789", [Validators.required, Validators.maxLength(9), Validators.minLength(9)]],
    numero: [0, Validators.required],
    calle: ["", Validators.required]})


    register = this.formBuilder.group({
    nombre: ["luca", [Validators.required]],
    apellidos: ["luca", Validators.required],
    email: ["luca", [Validators.required, Validators.email]],
    password: ["luca", Validators.required],
    telefono: ["luca", Validators.required],
    direccion: this.grupo.value as Direccion
  })


  logueo: LoginRequest | undefined 
  



  registrar() {
    if (this.register.valid) {
      this.registerService.register(this.register.value as RegisterComponent).subscribe({
        next: (userData) =>{
          this.login.logout();
          console.log(userData)
        },
        error:(userData) => {
            console.log(userData)
            
            this.router.navigateByUrl("/inicioSesion")
            alert("Error al registrar sesion")
        },
        complete:()=> {
          this.router.navigateByUrl("/inicioSesion")
        },
      }
      )
      console.log(this.register.value)
    } else {
      this.register.markAllAsTouched();
      alert("Error al loguear");
    }
  }

  constructor(private formBuilder: FormBuilder, private router: Router, private registerService: RegisterService,private login:LoginService) { }

  ngOnInit(): void { }
}
