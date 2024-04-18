package com.example.demo.Servicios;

import java.util.HashMap;
import java.util.List;

import org.apache.catalina.connector.Response;
import org.hibernate.mapping.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.Entidades.AuthResponse;
import com.example.demo.Entidades.Cita;
import com.example.demo.Entidades.Direccion;
import com.example.demo.Entidades.Medico;
import com.example.demo.Entidades.Paciente;
import com.example.demo.Entidades.Role;
import com.example.demo.Entidades.Sala;
import com.example.demo.Entidades.TiposRole;
import com.example.demo.Entidades.Validacion;
import com.example.demo.Error.Error;
import com.example.demo.IMAP.GenerarRandomValidacion;
import com.example.demo.IMAP.GestorEmail;
import com.example.demo.Repositorio.CitaRepositorio;
import com.example.demo.Repositorio.MedicoRepositorio;
import com.example.demo.Repositorio.PacienteRepositorio;
import com.example.demo.Repositorio.UserRepository;

import lombok.RequiredArgsConstructor;

import com.example.demo.JWT.JwsService;
/**
 * MedicoServicio
 */
@Service
@RequiredArgsConstructor
public class CitaServicio {

    GestorEmail gestor = new GestorEmail();
    @Autowired
    public UserRepository repository;

    @Autowired
    public MedicoRepositorio repositoryMedico;


    @Autowired
    public CitaRepositorio repositoryCita;


    @Autowired
    public PacienteRepositorio repoPaciente;
    public ResponseEntity<Cita> verCita (String email) {
        return ResponseEntity.ok().body(repositoryCita.findByPacienteEmail(email).get());
    }

    public ResponseEntity<HashMap<String, Object>> subirCitaPaciente(String email,Cita cita) {
        try {
            HashMap<String, Object> map = new HashMap<>();
            Paciente paciente = (Paciente) repository.findByEmail(email).get();
            gestor.setCorreoReceptor(paciente.getEmail());
            gestor.enviarMensajeTexto(gestor.MensajeCita(cita));
            paciente.setCita(cita);
            repository.save(paciente);
            map.put("Cita realizada", cita);
            return ResponseEntity.ok(map);
        } catch (Exception e) {
            HashMap<String, Object> map = new HashMap<>();
            Error error = new Error();
            error.setError(e.getMessage());
            map.put(error.getError(), error);
            return ResponseEntity.status(500).body(map);
        }
    }

    public ResponseEntity<Paciente> borrarCitaPaciente(String email) {
        try {
            Paciente paciente = (Paciente) repoPaciente.findByEmail(email).get();
            paciente.setCita(null);
            repoPaciente.save(paciente);
            return ResponseEntity.ok(paciente);
        } catch (Exception e) {
            Paciente cita = new Paciente();
            return ResponseEntity.status(500).body(cita);
        }
    }
    public ResponseEntity<HashMap<String, Object>> subirCitaMedico(String email,Cita cita) {
        try {
            Medico medico = (Medico) repository.findByEmail(email).get();
            HashMap<String, Object> map = new HashMap<>();
            gestor.setCorreoReceptor(medico.getEmail());
            gestor.enviarMensajeTexto(gestor.MensajeCita(cita));
            medico.setCita(cita);
            repository.save(medico);
            map.put("Cita realizada", cita);
            return ResponseEntity.ok(map);
        } catch (Exception e) {
            HashMap<String, Object> map = new HashMap<>();
            Error error = new Error();
            error.setError(e.getMessage());
            map.put(error.getError(), error);
            return ResponseEntity.status(500).body(map);
        }
    }
}
