package com.example.demo.Controlador;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Entidades.AuthResponse;
import com.example.demo.Entidades.Medico;
import com.example.demo.Entidades.Register;
import com.example.demo.Entidades.User;
import com.example.demo.Servicios.AuthService;
import com.example.demo.Servicios.MedicoServicio;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminControlador {
    @Autowired
    MedicoServicio servicio;

    @Autowired
    AuthService servicioAdmin;

    @PostMapping("/medicos")
	public ResponseEntity<List<Medico>> listaMedico() {
		return servicio.lista();
	}
    @GetMapping("/usuarios")
    public ResponseEntity<List<User>> listaUsuarios() {
		return servicioAdmin.verUsuarios();
	}
    
}
