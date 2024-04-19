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
  motivos: string
  tipoCita: TipoCita
  horario: Horario
}

export interface TipoCita {
  tipoCitaString: string
}

export interface Horario {
  idHorario: number
  dia: number
  mes: number
  horaInicio: number
  horaFinal: number
}

export interface Role {
  idRole: number
  role: string
}
