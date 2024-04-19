import { Component } from '@angular/core';
import { LoginService } from '../../../services/auth/login.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-validate',
  templateUrl: './validate.component.html',
  styleUrl: './validate.component.css'
})
export class ValidateComponent {
constructor(private login:LoginService, private router: Router){}

codigo: string = ""
validar(){
  this.login.validar(this.codigo).subscribe({
    next:(userOn)=>{
      console.info(userOn)
      this.router.navigateByUrl("inicio");
    },error:(userOn)=>{
      console.info(userOn)
      this.router.navigateByUrl("inicio");
    },complete:()=>{
      console.info("Completo")
      this.router.navigateByUrl("inicio");
    }
  })
}
}
