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
import com.example.demo.Entidades.Direccion;
import com.example.demo.Entidades.Medico;
import com.example.demo.Entidades.Paciente;
import com.example.demo.Entidades.Role;
import com.example.demo.Entidades.Sala;
import com.example.demo.Entidades.TiposRole;
import com.example.demo.Entidades.Validacion;
import com.example.demo.IMAP.GenerarRandomValidacion;
import com.example.demo.IMAP.GestorEmail;
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
public class PacienteServicio {


    private final JwsService jwtService;
    private final BCryptPasswordEncoder passwordEncoderr;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public UserRepository repository;

    @Autowired
    public PacienteRepositorio repositoryMedico;

    GestorEmail gestor = new GestorEmail();
    GenerarRandomValidacion genera;

    public ResponseEntity<HashMap<String, Object>> subirPaciente(Paciente medico) {
        try {
            HashMap<String, Object> map = new HashMap<>();
            Role role = new Role();
            role.setRole(TiposRole.PACIENTE);
            medico.setRole(role);
                Validacion validacion = new Validacion();
                validacion.setCodigoValidacion(genera.generarRandom());
                validacion.setValido(false);
                medico.setPassword(passwordEncoderr.encode(medico.getPassword()));
                medico.setValidacion(validacion);
                repository.save(medico);
                gestor.setCorreoReceptor(medico.getEmail());
                gestor.enviarMensajeTexto(gestor.MensajeValidacion(medico));
                map.put(AuthResponse.builder().token(jwtService.getToken(medico)).build().getToken(), medico);
                return ResponseEntity.status(201).body(map);
        } catch (Exception e) {
            HashMap<String, Object> map = new HashMap<>();
            com.example.demo.Error.Error error = new com.example.demo.Error.Error();
            error.setError(e.getMessage());
            map.put(error.getError(), error);
            return ResponseEntity.status(500).body(map);
        }

    }

    public ResponseEntity<String> updatePaciente(Long id, Direccion nuevosDatos) {
        try {
            // Buscar al médico por su ID
            Paciente medico = (Paciente) repository.findById(id).get();
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

            // Devolver la respuesta con el estado HTTP y el mensaje de confirmación
            return ResponseEntity.status(201).body(datoModificado.toString());
        } catch (Exception e) {
            // Si ocurre algún error, devolver un estado HTTP de error y un mensaje de error
            return ResponseEntity.status(500).body("Error");
        }
    }

    public ResponseEntity<String> deleteMedico(Long id) {
        try {
            if (repository.existsById(id)) {
                repository.deleteById(id);
                return ResponseEntity.status(201).body("Medico eliminado");
            } else {
                return ResponseEntity.status(500).body("No existe el medico");
            }

        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error");
        }
    }
    public ResponseEntity<List<Paciente>> lista() {
        return ResponseEntity.ok(repositoryMedico.findAll());
    }
}
