import { Component, OnInit } from '@angular/core';
import { LoginService } from '../../../services/auth/login.service';

@Component({
  selector: 'app-principal',
  templateUrl: './principal.component.html',
  styleUrl: './principal.component.css'
})
export class PrincipalComponent implements OnInit{

  constructor(private login:LoginService) { 
    
  }
  ngOnInit(): void {}

  cerrarSesion(){
    this.login.logout();
  }
  
}
