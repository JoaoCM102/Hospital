package com.example.demo.Repositorio;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Entidades.Cita;
import com.example.demo.Entidades.User;
import com.example.demo.Entidades.Validacion;

@Repository
public interface ValidacionRepositorio extends JpaRepository<Validacion,Long> {
    Optional<Validacion> findByUserEmail(String email);

}
