import { Component } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { CitaRequest, Horario, TipoCita } from '../../../services/auth/registerRequest';
import { PrincipalService } from '../../../services/start/principal.service';

@Component({
  selector: 'app-pedir-cita',
  templateUrl: './pedir-cita.component.html',
  styleUrls: ['./pedir-cita.component.css']
})
export class PedirCitaComponent {
  horario = this.formBuilder.group({
    dia: [5, Validators.required],
    mes: [0, Validators.required],
    horaInicio: [0, Validators.required],
    horaFinal: [0, Validators.required],
  });

  tipo: TipoCita = {
    tipoCitaString: "Presencial"
   }
  

  cita = this.formBuilder.group({
    motivos: ["", Validators.required],
    tipoCita: this.tipo as TipoCita,
    horario: this.horario.value as Horario
  });

  constructor(private formBuilder: FormBuilder, private principal: PrincipalService) {}

  subirCitaT() {
    if (this.tipo) {
      this.tipo.tipoCitaString = "Telefonica";
    }
    console.info(this.cita.value as CitaRequest) 
    this.principal.subirCita(this.cita.value as CitaRequest).subscribe({
      next: (cita) => {
        console.info(cita)
      },
      error:(cita) => {
        console.info(cita, "Error")
      },
      complete:()=> {
        console.info("Completo")        
      }
    })
  }
  subirCitaP() {
    if (this.tipo) {
      this.tipo.tipoCitaString = "Telefonica";
    }
    console.info(this.cita.value as CitaRequest) 
    this.principal.subirCita(this.cita.value as CitaRequest).subscribe({
      next: (cita) => {
        console.info(cita)
      },
      error:(cita) => {
        console.info(cita, "Error")
      },
      complete:()=> {
        console.info("Completo")        
      }
    })
  }
}
