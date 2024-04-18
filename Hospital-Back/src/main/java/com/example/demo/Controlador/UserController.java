package com.example.demo.Controlador;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Entidades.AuthResponse;
import com.example.demo.Entidades.Login;
import com.example.demo.Entidades.Register;
import com.example.demo.Entidades.User;
import com.example.demo.Servicios.AuthService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/auth")
@RequiredArgsConstructor
public class UserController {
	
	private final AuthService auth ;
	
	@PostMapping("/login")
	public ResponseEntity<AuthResponse> login(@RequestBody Login request) {
		return ResponseEntity.ok(auth.login(request));
	}
	
	@PostMapping("/register")
	public ResponseEntity<AuthResponse> register(@RequestBody Register request) {
		return ResponseEntity.ok(auth.register(request));
	}


	@GetMapping("/validate/{email}/{code}")
	public ResponseEntity<String> validar(@PathVariable String email, @PathVariable String code) {
		return auth.validate(email, code);
	}

	@GetMapping("/misDatos/{email}")
	public ResponseEntity<User> misDatos(@PathVariable String email) {
		return auth.misDatos(email);
	}
	
}
