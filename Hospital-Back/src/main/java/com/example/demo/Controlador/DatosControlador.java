package com.example.demo.Controlador;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Entidades.AuthResponse;
import com.example.demo.Entidades.Medico;
import com.example.demo.Entidades.Register;
import com.example.demo.Entidades.Role;
import com.example.demo.Entidades.TiposRole;
import com.example.demo.Entidades.User;
import com.example.demo.Servicios.AuthService;
import com.example.demo.Servicios.MedicoServicio;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/datos")
@RequiredArgsConstructor
public class DatosControlador {
    @Autowired
    MedicoServicio servicio;

    @Autowired
    AuthService auth;
    @GetMapping("/misDatos/{email}")
	public ResponseEntity<User> misDatos(@PathVariable String email) {
		return auth.misDatos(email);
	}

    @GetMapping("/contacto/{email}/{mensaje}")
    public ResponseEntity<String> getMethodName(@PathVariable String email, @PathVariable String mensaje) {
        return auth.contactanos(email, mensaje);
    }

    @GetMapping("/role/{email}")
    public ResponseEntity<Role> verRole(@PathVariable String email) {
        return auth.getRole(email);
    }
    
}
