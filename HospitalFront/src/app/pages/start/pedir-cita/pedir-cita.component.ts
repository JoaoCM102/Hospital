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

  tipo: string | null = "Cabezera";
  

  cita = this.formBuilder.group({
    motivos: ["", Validators.required],
    tipoCita: [{ value: this.tipo?.valueOf as unknown as TipoCita, disabled: false }],
    horario: this.horario
  });

  constructor(private formBuilder: FormBuilder, private principal: PrincipalService) {}

  subirCitaT() {
    this.tipo= "Telefonica"
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
    this.tipo= "Presencial"
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
