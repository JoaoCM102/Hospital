import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { BehaviorSubject, Observable, catchError, map, tap, throwError } from 'rxjs';
import { enviroment } from '../url';
import { LoginRequest } from './registerRequest';


@Injectable({
  providedIn: 'root'
})
export class LoginService {

  currentUserLogin: BehaviorSubject<boolean> = new BehaviorSubject<boolean>(false);
  currentUserData: BehaviorSubject<string> = new BehaviorSubject<string>("");
  id:Number=0

  constructor(private http:HttpClient) {
    this.currentUserLogin= new BehaviorSubject<boolean>(sessionStorage.getItem("token")!=null);
    this.currentUserData= new BehaviorSubject<string>(sessionStorage.getItem("token")|| "");
    
   }

   
  login(credentials:LoginRequest):Observable<any>{
   return this.http.post<any>(enviroment.urlHost+"auth/login",credentials).pipe(
    tap((userData)=> {
    sessionStorage.setItem("token",userData.token);
    this.currentUserData.next(userData);
    this.currentUserLogin.next(true);

    }),
    map((userData)=> userData.token),
    catchError(this.handleError)
   );
  }
  validar(codigo :string): Observable<string> {
    return this.http.get<string>(`${enviroment.urlHost}auth/validate/${obtenerSubDelToken(sessionStorage.getItem(`token`)!)}/${codigo}`).pipe(
      catchError(this.handleError)
    );
  }

  comprobarValidate(): Observable<boolean> {
    return this.http.get<boolean>(`${enviroment.urlHost}auth/comprobarValidate/${obtenerSubDelToken(sessionStorage.getItem(`token`)!)}`).pipe(
      catchError(this.handleError)
    );
  }

  logout(): void{
    sessionStorage.removeItem("token");
    this.currentUserLogin.next(false);
  }

  private handleError(error:HttpErrorResponse){
    if(error.status===0){
      console.error('Error', error.error);
    }else{console.error('Backend retorno el codigo', error.status)}
    return throwError(()=> new Error("Algo fallo , "+ error.message))
  }

  get userData():Observable<string>{
    return this.currentUserData.asObservable();
  }

  get userLoginOn():Observable<boolean>{
    return this.currentUserLogin.asObservable();
  }

  get token():string{
    return this.currentUserData.value;
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