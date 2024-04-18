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
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Validated
@Entity
@Getter
@Setter
public class TipoMedico {
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idTipoMedico;

    @Basic
    @Column(nullable = false)
    private String tipoMedicoString;


    @OneToOne(
		mappedBy = "tipoMedico"
	)
	@JsonIgnore
	private Medico medico;
}
