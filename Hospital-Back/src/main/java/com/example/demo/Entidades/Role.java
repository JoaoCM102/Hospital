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
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Validated
@Entity
public class Role {

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idRole;

    @Basic
    @Column(nullable = false)
    private TiposRole role;

    @OneToOne(
		mappedBy = "role"
	)
    @JsonIgnore
	private User user;
}
