import { Component, OnInit } from '@angular/core';
import { Direccion, RegisterComponent, Root } from '../../../services/auth/registerRequest';
import { AdminService } from '../../../services/admin.service';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrl: './admin.component.css'
})
export class AdminComponent implements OnInit{
  constructor(private admin: AdminService){}
  ngOnInit(): void {
    this.admin.Usuarios().subscribe({
      next:(userOn)=>{
        this.UserDato=userOn;
        console.info(userOn)
      },error:(userOn)=>{
        console.info(userOn)
      },complete:()=>{
        console.info("Completo")
      }
    })
  }
  direccionClick(){
    this.direccion = this.UserDato?.find(x => x.id == 1)?.direccion;
  }
  UserDato?:Root[];
  direccion?: Direccion 
}
