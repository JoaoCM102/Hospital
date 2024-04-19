import { Component, OnInit } from '@angular/core';
import { LoginService } from '../../../services/auth/login.service';
import { PrincipalService } from '../../../services/start/principal.service';
import { Role } from '../../../services/auth/registerRequest';

@Component({
  selector: 'app-principal',
  templateUrl: './principal.component.html',
  styleUrl: './principal.component.css'
})
export class PrincipalComponent implements OnInit{

  role: Role | undefined;
  verOn:boolean= false
  validadoOn:boolean= false
  constructor(private login:LoginService, private principal : PrincipalService) { }
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
    this.principal.role().subscribe({
      next:(userOn)=>{
        this.role=userOn;
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
