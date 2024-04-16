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
	@NotBlank
    private int dia;
	@Basic
	@Column(nullable = false)
	@NotBlank
    private int mes;

	@NotBlank(message = "El campo nombre esta vacio")
    private Time horaInicio;
	
    @NotBlank(message = "El campo nombre esta vacio")
    private Time horaFinal;

    @NotBlank(message = "El campo nombre esta vacio")
    private boolean disponibilidad;
	@OneToOne(
		mappedBy = "horario"
	)
	@JsonIgnore
	private Cita cita;
}
