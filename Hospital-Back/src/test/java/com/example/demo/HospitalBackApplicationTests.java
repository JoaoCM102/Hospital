package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.Entidades.Medico;
import com.example.demo.Entidades.TipoMedico;
import com.example.demo.Entidades.User;
import com.example.demo.Repositorio.UserRepository;

@SpringBootTest
class HospitalBackApplicationTests {


	UserRepository repository;

	@Test
	void anyadirUser(){
		TipoMedico tipo = new TipoMedico();
		Medico medico = new Medico();
medico.setId(1); // ID del médico
medico.setEmail("medico@example.com");
medico.setPassword("contraseña");
medico.setNombre("Dr. Juan");
medico.setApellidos("Pérez");
medico.setTelefono("123456789");
tipo.setTipoMedicoString("Cabezera");
medico.setTipoMedico(tipo);
		repository.save(medico);
	}
	@Test
	void contextLoads() {
	}

}
