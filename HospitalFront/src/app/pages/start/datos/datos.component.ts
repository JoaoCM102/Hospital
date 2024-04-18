import { Component } from '@angular/core';
import { RegisterComponent } from '../../../services/auth/registerRequest';

@Component({
  selector: 'app-datos',
  templateUrl: './datos.component.html',
  styleUrl: './datos.component.css'
})
export class DatosComponent {

RegisterComponent : RegisterComponent | undefined
constructor(){}

}
