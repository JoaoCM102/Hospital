package com.example.demo.Servicios;

import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import com.example.demo.Entidades.Direccion;
import com.example.demo.Entidades.Medico;
import com.example.demo.Entidades.Role;
import com.example.demo.Entidades.Sala;
import com.example.demo.Entidades.TiposRole;
import com.example.demo.Entidades.Validacion;
import com.example.demo.Repositorio.MedicoRepositorio;
import com.example.demo.Repositorio.UserRepository;

/**
 * MedicoServicio
 */
public class MedicoServicio {

    @Autowired
    public
    UserRepository repository;

    @Autowired
    public
    MedicoRepositorio repositoryMedico;

    public ResponseEntity<Medico> subirMedico(Medico medico) {
        Validacion validacion = new Validacion();
        Sala sala = new Sala();
        validacion.setValido(false);
        medico.setSala(sala);
        medico.setValidacion(validacion);
        repository.save(medico);
        return ResponseEntity.status(201).body(medico);
    }

    public ResponseEntity<String> updateMedico(Long id, Direccion nuevosDatos) {
        try {
            // Buscar al médico por su ID
            Medico medico = (Medico) repository.findById(id).get();
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
            if (repository.existsById(id) ) {
                repository.deleteById(id);
                return ResponseEntity.status(201).body("Medico eliminado");
            }else{
                return ResponseEntity.status(500).body("No existe el medico");
            }
            
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error");
        }
    }

    public ResponseEntity<List<Medico>> lista(Long idMedico) {
        return ResponseEntity.ok(repositoryMedico.findAll());
    }
}

    
