package com.example.demo.Controlador;

import java.util.HashMap;

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
import com.example.demo.Servicios.MedicoServicio;

import lombok.RequiredArgsConstructor;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminControlador {
    @Autowired
    MedicoServicio servicio;

    @PostMapping("/medicoRegister")
	public ResponseEntity<HashMap<String, Object>> registerMedico(@RequestBody Medico request) {
		return servicio.subirMedico(request);
	}
}
