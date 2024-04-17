package com.example.demo.Entidades;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.Entidades.Login;
import com.example.demo.Entidades.Register;
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

    public AuthResponse login(Login request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        UserDetails user=userRepository.findByEmail(request.getEmail()).orElseThrow();
        String token=jwtService.getToken(user);
        return AuthResponse.builder()
            .token(token)
            .build();
    }

	public AuthResponse register(Register request) {
		
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
		userRepository.save(user);
		
		return AuthResponse.builder()
				.token(jwtService.getToken(user))
				.build();
	}

}
