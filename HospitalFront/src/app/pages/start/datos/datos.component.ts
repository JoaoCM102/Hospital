import { Component } from '@angular/core';
import { RegisterComponent } from '../../../services/auth/registerRequest';
import { PrincipalService } from '../../../services/start/principal.service';

@Component({
  selector: 'app-datos',
  templateUrl: './datos.component.html',
  styleUrl: './datos.component.css'
})
export class DatosComponent {

RegisterComponent : RegisterComponent | null = null;
constructor(private principal:PrincipalService){}
ngOnInit(): void {
  this.principal.verDatos().subscribe({
    next: (cita) => {
      console.info(cita)
      this.RegisterComponent = cita
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
