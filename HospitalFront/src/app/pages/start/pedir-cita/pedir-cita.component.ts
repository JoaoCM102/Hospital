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

  tipoCita = this.formBuilder.group({
    tipoCitaString: ["", Validators.required] // Inicializar sin un valor por defecto
  });

  cita = this.formBuilder.group({
    motivos: ["", [Validators.required]],
    tipoCita: this.tipoCita.value as TipoCita,
    horario: this.horario.value as Horario
  });

  constructor(private formBuilder: FormBuilder, private principal: PrincipalService) {}

  subirCita() {
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
  toggleCheckbox(checkboxId: string) {
    const otherCheckboxId = checkboxId === 'telefonica' ? 'presencial' : 'telefonica';
    const checkbox = document.getElementById(checkboxId) as HTMLInputElement;
    const otherCheckbox = document.getElementById(otherCheckboxId) as HTMLInputElement;

    if (checkbox.checked) {
      otherCheckbox.checked = false;
      this.tipoCita.patchValue({
        tipoCitaString: checkboxId === 'telefonica' ? 'Telefónica' : 'Presencial'
      });
    } else {
      this.tipoCita.patchValue({
        tipoCitaString: "" // Si el checkbox se desmarca, se reinicia el valor
      });
    }
  }
}
