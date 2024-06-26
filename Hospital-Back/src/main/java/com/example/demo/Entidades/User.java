package com.example.demo.Entidades;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name="user", uniqueConstraints = {@UniqueConstraint(columnNames = {"email"})})
public class User implements UserDetails {
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @Basic
    @Column(nullable = false)
    String email;
    @Column(nullable = false)
    String password;
    @Basic
	@Column(nullable = false)


	@NotBlank
    private String nombre;
	@Basic
	@Column(nullable = false)
	@NotBlank(message = "El campo nombre esta vacio")
    private String apellidos;

	@NotBlank(message = "El campo nombre esta vacio")
    private String telefono;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(
    		name = "role",
    		referencedColumnName = "idRole")
	private Role role;

	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(
    		name = "direccion",
    		referencedColumnName = "idDireccion")
	private Direccion direccion;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(
    		name = "validacion",
    		referencedColumnName = "idValidacion")
	private Validacion validacion;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority((role.getRole().name())));
    }



    @Override
    public String getUsername() {
        return this.email;
    }



    @Override
    public boolean isAccountNonExpired() {
       return true;
    }
    @Override
    public boolean isAccountNonLocked() {
       return true;
    }
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    @Override
    public boolean isEnabled() {
        return true;
    }
     


}
