import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { CitaRequest, RegisterComponent, Role } from '../auth/registerRequest';
import { Observable, catchError, tap, throwError } from 'rxjs';
import { enviroment } from '../url';

@Injectable({
  providedIn: 'root'
})
export class PrincipalService {

  constructor(private Http:HttpClient) { }

  verDatosCita(): Observable<CitaRequest> {
    const headers = new HttpHeaders().set('Authorization', `Bearer ${sessionStorage.getItem(`token`)}`);

    return this.Http.get<CitaRequest>(`${enviroment.urlHost}cita/ver/${obtenerSubDelToken(sessionStorage.getItem(`token`)!)}` ,{ headers }).pipe(
      catchError(this.handleError)
    );
  }

  subirCita(cita:CitaRequest): Observable<CitaRequest> {
    const headers = new HttpHeaders().set('Authorization', `Bearer ${sessionStorage.getItem(`token`)}`);
    console.info(headers)
    return this.Http.post<any>(`${enviroment.urlHost}cita/subirCitaPaciente/${obtenerSubDelToken(sessionStorage.getItem(`token`)!)}`,cita , { headers }).pipe(
      catchError(this.handleError)
    );
  }

  borrarCita(): Observable<void> {
    const headers = new HttpHeaders().set('Authorization', `Bearer ${sessionStorage.getItem(`token`)}`);
    return this.Http.delete<void>(`${enviroment.urlHost}cita/cancelarCita/${obtenerSubDelToken(sessionStorage.getItem(`token`)!)}`,{ headers }).pipe(
      
    )
  }

  verDatos(): Observable<RegisterComponent> {
    const headers = new HttpHeaders().set('Authorization', `Bearer ${sessionStorage.getItem(`token`)}`);

    return this.Http.get<RegisterComponent>(`${enviroment.urlHost}datos/misDatos/${obtenerSubDelToken(sessionStorage.getItem(`token`)!)}`,{ headers }).pipe(
      catchError(this.handleError)
    );
  }

  contactar(mensaje: string): Observable<string> {
    const headers = new HttpHeaders().set('Authorization', `Bearer ${sessionStorage.getItem(`token`)}`);

    return this.Http.get<string>(`${enviroment.urlHost}datos/contacto/${obtenerSubDelToken(sessionStorage.getItem(`token`)!)}/${mensaje}`,{ headers }).pipe(
      catchError(this.handleError)
    );
  }

  role(): Observable<Role> {
    const headers = new HttpHeaders().set('Authorization', `Bearer ${sessionStorage.getItem(`token`)}`);

    return this.Http.get<Role>(`${enviroment.urlHost}datos/role/${obtenerSubDelToken(sessionStorage.getItem(`token`)!)}`,{ headers }).pipe(
      tap(userData => console.log(userData)),
      catchError(this.handleError)
    );
  }
  private handleError(error:HttpErrorResponse){
    if(error.status===0){
      console.error('Error', error.error);
    }else{console.error('Backend retorno el codigo', error.status)}
    return throwError(()=> new Error("Algo fallo , "+ error.message))
  }
  
}


function obtenerSubDelToken(token:string): string {
  try {
    const tokenPayload = token.split('.')[1];
    const decodedPayload = atob(tokenPayload);
    const decodedToken = JSON.parse(decodedPayload);

      // Devuelve el campo 'sub' del token decodificado
      return decodedToken.sub;
  } catch (error) {
      console.error('Error al obtener el sub del token:');
      return "error";
  }
}