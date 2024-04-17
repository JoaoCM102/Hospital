package com.example.demo.Entidades;

import com.example.demo.Entidades.Role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Register {
	String email;
	String password;
	String nombre;
	String apellidos;
	String telefono;
	Direccion direccion;
	Role role;
	Validacion validacion;
	
}
