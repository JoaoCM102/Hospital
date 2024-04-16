package com.example.demo.Entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Basic;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Validacion {
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Basic
    String codigoValidacion;

    boolean valido;

    @OneToOne(
		mappedBy = "validacion"
	)
	@JsonIgnore
	private User user;

}
