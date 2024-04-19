package com.example.demo.Entidades;

import org.springframework.validation.annotation.Validated;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.Data;

@Data
@Validated
@Entity
@PrimaryKeyJoinColumn(name = "idAdmin")
public class Admin extends User{
	@Basic
    @Column(nullable = false)
	private String supervisor ;
}

