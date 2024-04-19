import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, tap, throwError } from 'rxjs';
import { Role, Root } from './auth/registerRequest';
import { enviroment } from './url';

@Injectable({
  providedIn: 'root'
})
export class AdminService {

  constructor(private Http : HttpClient) { }
  Usuarios(): Observable<Root[]> {
    const headers = new HttpHeaders().set('Authorization', `Bearer ${sessionStorage.getItem(`token`)}`);

    return this.Http.get<Root[]>(`${enviroment.urlHost}api/admin/usuarios`,{ headers }).pipe(
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