import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './pages/auth/login/login.component';
import { RegisterComponent } from './pages/auth/register/register.component';
import { RegisterMedicoComponent } from './pages/auth/register-medico/register-medico.component';
import { PrincipalComponent } from './pages/start/principal/principal.component';
import { PedirCitaComponent } from './pages/start/pedir-cita/pedir-cita.component';
import { DatosComponent } from './pages/start/datos/datos.component';
import { ContactoComponent } from './pages/start/contacto/contacto.component';
import { UbicacionComponent } from './pages/start/ubicacion/ubicacion.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterComponent,
    RegisterMedicoComponent,
    PrincipalComponent,
    PedirCitaComponent,
    DatosComponent,
    ContactoComponent,
    UbicacionComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
