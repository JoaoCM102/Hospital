package com.example.demo.Servicios;

import java.io.IOException;
import java.util.List;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.Entidades.AuthResponse;
import com.example.demo.Entidades.Login;
import com.example.demo.Entidades.Register;
import com.example.demo.Entidades.Role;
import com.example.demo.Entidades.TiposRole;
import com.example.demo.Entidades.User;
import com.example.demo.Entidades.Validacion;
import com.example.demo.IMAP.GenerarRandomValidacion;
import com.example.demo.IMAP.GestorEmail;
import com.example.demo.JWT.JwsService;
import com.example.demo.Repositorio.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

	private final UserRepository userRepository;
	private final JwsService jwtService;
	private final BCryptPasswordEncoder passwordEncoderr;
	private final AuthenticationManager authenticationManager;

	GestorEmail gestor = new GestorEmail();
	public AuthResponse login(Login request) {
		authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
		UserDetails user = userRepository.findByEmail(request.getEmail()).orElseThrow();
		String token = jwtService.getToken(user);
		return AuthResponse.builder()
				.token(token)
				.build();
	}

	public AuthResponse register(Register request) {
		Role role = new Role();
		role.setRole(TiposRole.PACIENTE);
		Validacion validacion = new Validacion();
		validacion.setValido(false);
		validacion.setCodigoValidacion(GenerarRandomValidacion.generarRandom());
		request.setValidacion(validacion);
		request.setRole(role);
		User user = User.builder()
				.email(request.getEmail())
				.password(passwordEncoderr.encode(request.getPassword()))
				.nombre(request.getNombre())
				.apellidos(request.getApellidos())
				.telefono(request.getTelefono())
				.direccion(request.getDireccion())
				.role(request.getRole())
				.validacion(request.getValidacion())
				.build();
		gestor.setCorreoReceptor(user.getEmail());
        try {
			gestor.enviarMensajeTexto(gestor.MensajeValidacion(user));
		} catch (MessagingException | IOException e) {
			
			e.printStackTrace();
		}
		userRepository.save(user);

		return AuthResponse.builder()
				.token(jwtService.getToken(user))
				.build();
	}

	public ResponseEntity<String> validate(Long id, String code) {
		try	{	
			if (userRepository.existsById(id)) {
			User user = userRepository.findById(id).get();
			if (user.getValidacion().getCodigoValidacion().equals(code)) {
				user.getValidacion().setValido(true);
				userRepository.save(user);
				return ResponseEntity.ok("Validado");
			}else{
				return ResponseEntity.ok("Codigo incorrecto");
			}
		} else {
			return ResponseEntity.ok("No existe el usuario");
		}
		}catch(Exception e)	{	
			return ResponseEntity.status(500).body(e.getMessage());
		}
		

	}

	public ResponseEntity<User> misDatos(Long id) {
		return ResponseEntity.ok().body(userRepository.findById(id).get());
	}
	public ResponseEntity<List<User>> verUsuarios(){
		return ResponseEntity.ok(userRepository.findAll());
	}
}