import { Component, OnInit } from '@angular/core';
import { LoginService } from '../../../services/auth/login.service';

@Component({
  selector: 'app-principal',
  templateUrl: './principal.component.html',
  styleUrl: './principal.component.css'
})
export class PrincipalComponent implements OnInit{

  verOn:boolean= false
  validadoOn:boolean= false
  constructor(private login:LoginService) { }
  ngOnInit(): void {
    this.login.currentUserLogin.subscribe({
      next:(userOn)=>{
        this.verOn=userOn;
        console.info(userOn)
      }
    });

    this.login.comprobarValidate().subscribe({
      next:(userOn)=>{
        this.validadoOn=userOn;
        console.info(userOn)
      },error:(userOn)=>{
        console.info(userOn)
      },complete:()=>{
        console.info("Completo")
      }
    })
  }

  cerrarSesion(){
    this.login.logout();
  }
  
}
