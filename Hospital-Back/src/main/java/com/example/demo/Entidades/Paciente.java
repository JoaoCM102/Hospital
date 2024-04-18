package com.example.demo.Entidades;

import org.springframework.validation.annotation.Validated;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Validated
@Entity
@PrimaryKeyJoinColumn(name = "idPaciente")
public class Paciente extends User{
	
	

	@OneToOne(
		cascade = CascadeType.ALL
	)
    @JoinColumn(
    		name = "Cita",
    		referencedColumnName = "idCita")
	private Cita cita;
}

