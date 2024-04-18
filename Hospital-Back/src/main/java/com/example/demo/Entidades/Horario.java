package com.example.demo.Entidades;

import java.sql.Time;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Validated
@Entity
public class Horario {
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idHorario;
	
	@Basic
	@Column(nullable = false)
    private int dia;
	@Basic
	@Column(nullable = false)
    private int mes;

    private String horaInicio;
	
    private String horaFinal;

	@OneToOne(
		mappedBy = "horario"
	)
	@JsonIgnore
	private Cita cita;
}
