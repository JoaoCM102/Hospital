package com.example.demo;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import com.example.demo.Entidades.Direccion;
import com.example.demo.Entidades.Medico;
import com.example.demo.Entidades.Role;
import com.example.demo.Entidades.Sala;
import com.example.demo.Entidades.TiposRole;
import com.example.demo.Entidades.Validacion;
import com.example.demo.Repositorio.MedicoRepositorio;
import com.example.demo.Repositorio.UserRepository;
import com.example.demo.Servicios.MedicoServicio;

public class MedicoServicioTest {

    @Mock
    UserRepository repository;

    @Mock
    MedicoRepositorio repositoryMedico;

    @Mock
    MedicoServicio servicio;

    @Test
    public void testSubirMedico() {
        // Arrange
        MockitoAnnotations.openMocks(this);
        servicio = new MedicoServicio(null, null, null);
        servicio.repository = repository;
        servicio.repositoryMedico = repositoryMedico;
        Medico medico = new Medico();

        // Act
        ResponseEntity<HashMap<String, Object>> response = servicio.subirMedico(medico);

        // Assert
        verify(repository, times(1)).save(medico);
        assertEquals(201, response.getStatusCodeValue());
        assertEquals(medico, response.getBody());
    }

    @Test
    public void testUpdateMedico() {
        // Arrange
        MockitoAnnotations.openMocks(this);
        servicio = new MedicoServicio(null, null, null);
        servicio.repository = repository;
        servicio.repositoryMedico = repositoryMedico;
        Long id = 1L;
        Direccion nuevosDatos = new Direccion();
        nuevosDatos.setMunicipio("Ciudad");
        nuevosDatos.setCalle("Calle Principal");
        nuevosDatos.setNumero(123);

        // Act
        Medico mockedMedico = mock(Medico.class);
        when(repository.findById(id)).thenReturn(Optional.of(mockedMedico));
        ResponseEntity<String> response = servicio.updateMedico(id, nuevosDatos);

        // Assert
        verify(repository, times(1)).findById(id);
        verify(repository, times(1)).save(mockedMedico);
        assertEquals(201, response.getStatusCodeValue());
    }

    @Test
    public void testDeleteMedico() {
        // Arrange
        MockitoAnnotations.openMocks(this);
        servicio = new MedicoServicio(null, null, null);
        servicio.repository = repository;
        servicio.repositoryMedico = repositoryMedico;
        Long id = 1L;

        // Act
        ResponseEntity<String> response = servicio.deleteMedico(id);

        // Assert
        verify(repository, times(1)).deleteById(id);
        assertEquals(201, response.getStatusCodeValue());
    }

    @Test
    public void testLista() {
        // Arrange
        MockitoAnnotations.openMocks(this);
        servicio = new MedicoServicio(null, null, null);
        servicio.repository = repository;
        servicio.repositoryMedico = repositoryMedico;
        Long id = 1L;

        // Act
        ResponseEntity<List<Medico>> response = servicio.lista();

        // Assert
        verify(repositoryMedico, times(1)).findAll();
        assertEquals(200, response.getStatusCodeValue());
    }
}

