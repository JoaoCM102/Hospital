package com.example.demo.Controlador;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Entidades.AuthResponse;
import com.example.demo.Entidades.Cita;
import com.example.demo.Entidades.Medico;
import com.example.demo.Entidades.Register;
import com.example.demo.Servicios.CitaServicio;
import com.example.demo.Servicios.MedicoServicio;

import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/cita")
@RequiredArgsConstructor
public class CitaControlador {
    @Autowired
    CitaServicio servicio;

    @GetMapping("/ver{email}")
    public ResponseEntity<Cita> verCitaPorEmail(@PathVariable String email) {
        return servicio.verCita(email);
    }
    
    @PostMapping("/subirCitaPaciente/{email}")
	public ResponseEntity<HashMap<String, Object>> subirCitaPaciente(@PathVariable String email,@RequestBody Cita request) {
		return servicio.subirCitaPaciente(email,request);
	}

    @PostMapping("/subirCitaMedico/{id}")
	public ResponseEntity<HashMap<String, Object>> subirCitaMedico(@PathVariable Long id,@RequestBody Cita request) {
		return servicio.subirCitaMedico(id,request);
	}

    @DeleteMapping("/cancelarCita/{email}")
    public ResponseEntity<String> cancelarCita(@PathVariable String email) {
        return servicio.borrarCitaPaciente(email);
    }
}
