package com.example.demo.Entidades;

import java.util.List;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Validated
@Entity
public class Sala {

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idSala;

    @OneToOne
    @JoinColumn(
    		name = "Horario",
    		referencedColumnName = "idHorario")
	private Horario horario;

    @OneToOne(
		mappedBy = "sala"
	)
	@JsonIgnore
	private Medico medico;
    
    @OneToMany(
    		cascade = CascadeType.ALL,
    		fetch = FetchType.EAGER)
    private List<Cita> citas;
}
