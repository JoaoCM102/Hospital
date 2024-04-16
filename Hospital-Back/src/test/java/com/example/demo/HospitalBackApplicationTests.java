package com.example.demo;

import java.io.IOException;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.Entidades.Direccion;
import com.example.demo.Entidades.Medico;
import com.example.demo.Entidades.Role;
import com.example.demo.Entidades.TipoMedico;
import com.example.demo.Entidades.TiposRole;
import com.example.demo.Entidades.User;
import com.example.demo.IMAP.GestorEmail;
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


	@Test
	void contextLoads2() throws AddressException, MessagingException, IOException {
		GestorEmail gestor = new GestorEmail();
		gestor.setCorreoReceptor("doncartcompany@gmail.com");
	}

	@Test
	void cambioMedico(){
		try {
			Direccion nuevosDatos = new Direccion();
			nuevosDatos.setMunicipio("Ciudad de Messi");
			nuevosDatos.setCalle("Calle Aleatoria");
			nuevosDatos.setNumero(123);
            // Buscar al médico por su ID
            Medico medico = (Medico) repository.findById(1L).get();
            StringBuilder datoModificado = new StringBuilder(); // StringBuilder para construir la respuesta

            // Actualizar los datos proporcionados si no son nulos o cero
            if (nuevosDatos != null) {
                if (nuevosDatos.getMunicipio() != null) {
                    medico.getDireccion().setMunicipio(nuevosDatos.getMunicipio());
                    datoModificado.append("Municipio modificado, ");
                }
                if (nuevosDatos.getCalle() != null) {
                    medico.getDireccion().setCalle(nuevosDatos.getCalle());
                    datoModificado.append("Calle modificada, ");
                }
                if (nuevosDatos.getNumero() != 0) {
                    medico.getDireccion().setNumero(nuevosDatos.getNumero());
                    datoModificado.append("Numero modificado");
                }
            }

            // Guardar los cambios en la base de datos
            repository.save(medico);

            // Devolver la respuesta con el estado HTTP y el mensaje de confirmac
        } catch (Exception e) {
            // Si ocurre algún error, devolver un estado HTTP 
        }
	}

}
