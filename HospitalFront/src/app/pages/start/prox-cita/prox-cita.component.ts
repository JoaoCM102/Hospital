import { Component, OnInit } from '@angular/core';
import { CitaRequest } from '../../../services/auth/registerRequest';
import { PrincipalService } from '../../../services/start/principal.service';

@Component({
  selector: 'app-prox-cita',
  templateUrl: './prox-cita.component.html',
  styleUrl: './prox-cita.component.css'
})
export class ProxCitaComponent implements OnInit{
  Cita: CitaRequest | undefined
  constructor(private principal:PrincipalService){}
  ngOnInit(): void {
    this.principal.verDatosCita().subscribe({
      next: (cita) => {
        console.info(cita)
        this.Cita = cita
      },
      error:(userData) => {
          console.log(userData)
          
      },
      complete:()=> {
        console.info("Completo")
      }
  });



}
}
