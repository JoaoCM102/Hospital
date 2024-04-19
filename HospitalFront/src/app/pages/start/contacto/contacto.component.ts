import { Component } from '@angular/core';
import { PrincipalService } from '../../../services/start/principal.service';
import { FormBuilder, Validators } from '@angular/forms';

@Component({
  selector: 'app-contacto',
  templateUrl: './contacto.component.html',
  styleUrl: './contacto.component.css'
})
export class ContactoComponent {
  constructor(private principal:PrincipalService,private formBuilder:FormBuilder){}
  mensaje : string = "";

  contactar(){
    this.principal.contactar(this.mensaje).subscribe({
      next: (msg) => {
        console.info(msg)
      },error:(msg) => {
        console.info(msg)
      },complete:()=> {
        console.info("Completo")
      }
    })
  }
}
