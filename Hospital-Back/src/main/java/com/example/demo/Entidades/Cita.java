package com.example.demo.Entidades;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Basic;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Validated
@Entity
public class Cita {
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idCita;

    @Basic
    private String motivos;

    @OneToOne
    @JoinColumn(
    		name = "TipoCita",
    		referencedColumnName = "idTipoCita")
	private TipoCita tipoCita;

    @OneToOne(
		mappedBy = "cita"
	)
	@JsonIgnore
	private Medico medico;

    @OneToOne
    @JoinColumn(
    		name = "Horario",
    		referencedColumnName = "idHorario")
	private Horario horario;

    @OneToOne(
		mappedBy = "cita"
	)
	@JsonIgnore
	private Paciente paciente;


}
