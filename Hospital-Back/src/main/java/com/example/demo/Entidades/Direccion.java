package com.example.demo.Entidades;

import org.springframework.validation.annotation.Validated;

import com.example.demo.Entidades.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
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
public class Direccion {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idDireccion;
	
	@Basic
	@Column(nullable = false)
	@NotBlank
    private String municipio;
	@Basic
	@Column(nullable = false)
	@NotBlank
    private String calle;
	
    private int numero;
	

	@OneToOne(
		mappedBy = "direccion"
	)
	@JsonIgnore
	private User user;
}

