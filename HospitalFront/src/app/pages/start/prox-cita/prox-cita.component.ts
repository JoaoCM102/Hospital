import { Component } from '@angular/core';
import { CitaRequest } from '../../../services/auth/registerRequest';

@Component({
  selector: 'app-prox-cita',
  templateUrl: './prox-cita.component.html',
  styleUrl: './prox-cita.component.css'
})
export class ProxCitaComponent {
  Cita: CitaRequest | undefined
  constructor(){}
}
