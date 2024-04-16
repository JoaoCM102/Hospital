package com.example.demo.Repositorio;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Entidades.Cita;
import com.example.demo.Entidades.User;

@Repository
public interface CitaRepositorio extends JpaRepository<Cita,Integer> {
  
}
