package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.Entidades.Direccion;
import com.example.demo.Entidades.Medico;
import com.example.demo.Entidades.Role;
import com.example.demo.Entidades.TipoMedico;
import com.example.demo.Entidades.TiposRole;
import com.example.demo.Entidades.User;
import com.example.demo.Repositorio.UserRepository;

@SpringBootTest(classes = HospitalBackApplication.class, properties = {"spring.autoconfigure.exclude=org.springframework.ai.autoconfigure.openai.OpenAiAutoConfiguration"})
class HospitalBackApplicationTests {

	@Autowired
	UserRepository repository;

	@Test
	void anyadirUser(){
		Role role = new Role();
        role.setRole(TiposRole.ADMIN);
		TipoMedico tipo = new TipoMedico();
		Medico medico = new Medico();
		Direccion direccion = new Direccion();
        direccion.setMunicipio("Ciudad de Ejemplo");
        direccion.setCalle("Calle Principal");
        direccion.setNumero(123);
medico.setId(1); // ID del médico
medico.setEmail("medico1@example.com");
medico.setPassword("contraseña");
medico.setNombre("Dr. Juan");
medico.setApellidos("Pérez");
medico.setTelefono("123456789");
medico.setDireccion(direccion);
medico.setRole(null);
tipo.setTipoMedicoString("Cabezera");
medico.setTipoMedico(tipo);
medico.setRole(role);
		repository.save(medico);
	}
	@Test
	void contextLoads() {
	}

}
