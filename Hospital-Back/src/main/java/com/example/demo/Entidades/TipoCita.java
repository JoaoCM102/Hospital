package com.example.demo.Entidades;

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
public class TipoCita {
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idTipoCita;

    @Basic
    @Column(nullable = false)
	@NotBlank(message = "falta el tipo")
    private String tipoCitaString;


    @OneToOne(
		mappedBy = "tipoCita"
	)
	@JsonIgnore
	private Cita cita;
}
