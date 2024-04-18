import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { CitaRequest } from '../auth/registerRequest';
import { Observable, catchError, throwError } from 'rxjs';
import { enviroment } from '../url';

@Injectable({
  providedIn: 'root'
})
export class PrincipalService {

  constructor(private Http:HttpClient) { }

  verDatosCita(email:string): Observable<CitaRequest> {
    const headers = new HttpHeaders().set('Authorization', `Bearer ${sessionStorage.getItem(`token`)}`);

    return this.Http.get<CitaRequest>(`${enviroment.urlHost}ver/datos/${email}`, { headers }).pipe(
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
