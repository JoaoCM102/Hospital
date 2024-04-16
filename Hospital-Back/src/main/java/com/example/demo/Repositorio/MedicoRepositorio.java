package com.example.demo.Repositorio;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Entidades.Medico;
import com.example.demo.Entidades.User;

@Repository
public interface MedicoRepositorio extends JpaRepository<Medico,Integer> {
   
}
