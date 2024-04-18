package com.example.demo.Entidades;

import java.util.List;

import org.springframework.validation.annotation.Validated;

import com.example.demo.Entidades.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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

