import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './pages/auth/login/login.component';
import { PrincipalComponent } from './pages/start/principal/principal.component';
import { RegisterComponent } from './pages/auth/register/register.component';
import { UbicacionComponent } from './pages/start/ubicacion/ubicacion.component';
import { DatosComponent } from './pages/start/datos/datos.component';
import { PedirCitaComponent } from './pages/start/pedir-cita/pedir-cita.component';
import { ContactoComponent } from './pages/start/contacto/contacto.component';
import { RegisterMedicoComponent } from './pages/auth/register-medico/register-medico.component';
import { ProxCitaComponent } from './pages/start/prox-cita/prox-cita.component';
import { LoginService } from './services/auth/login.service';

const routes: Routes = [
  {path:'',redirectTo:'inicioSesion' ,pathMatch:'full'},
  {path:'inicio', component:PrincipalComponent},
  {path:'inicioSesion',component:LoginComponent},
  {path:'registro',component:RegisterComponent},
  {path:'registroMedico',component:RegisterMedicoComponent},
  {path:'ubicacion',component:UbicacionComponent},
  {path:'cita',component:PedirCitaComponent},
  {path:'contactanos',component:ContactoComponent},
  {path:'datos',component:DatosComponent},
  {path:'citaProxima',component:ProxCitaComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {}
