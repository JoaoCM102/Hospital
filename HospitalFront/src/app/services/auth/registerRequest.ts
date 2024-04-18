export interface RegisterComponentMedico {
    email: string
    password: string
    nombre: string
    apellidos: string
    telefono: string
    direccion: Direccion
    tipoMedico: TipoMedico
    sala?: any
    cita?: any
  }
  export interface RegisterComponent {
    email: string
    password: string
    nombre: string
    apellidos: string
    telefono: string
    direccion: Direccion
  }
  export interface Direccion {
    municipio: string
    calle: string
    numero: number
  }
  
  export interface TipoMedico {
    tipoMedicoString: string
  }
  export interface LoginRequest{
    value: LoginRequest;
    username:string,
    password:string

}
export interface CitaRequest {
  idCita: number
  motivos: string
  tipoCita: TipoCita
  horario: Horario
}

export interface TipoCita {
  idTipoCita: number
  tipoMedicoString: string
}

export interface Horario {
  idHorario: number
  dia: number
  mes: number
  horaInicio: string
  horaFinal: string
}
