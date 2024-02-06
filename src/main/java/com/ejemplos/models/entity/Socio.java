package com.ejemplos.models.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name="socios")
public class Socio implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_socio;
	
	@NotNull
	@NotEmpty
	private String nombre;
	
	@NotNull
	@NotEmpty
	private String apellidos;
	
	@OneToMany(mappedBy="socio", fetch = FetchType.LAZY)
	private List<Libro> libros;
	
	public Socio() {
		this.libros = new ArrayList<Libro>();
	}

	public Socio(Long id_socio, @NotNull String nombre, @NotNull String apellidos) {
		super();
		this.libros = new ArrayList<Libro>();
		this.id_socio = id_socio;
		this.nombre = nombre;
		this.apellidos = apellidos;
	}

	public List<Libro> getLibros() {
		return libros;
	}
	public void setLibros(List<Libro> libros) {
		this.libros = libros;
	}

	public Long getId_socio() {
		return id_socio;
	}

	public void setId_socio(Long id_socio) {
		this.id_socio = id_socio;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Socio [id_socio=" + id_socio + ", nombre=" + nombre + ", apellidos=" + apellidos +  "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(id_socio);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Socio other = (Socio) obj;
		return Objects.equals(id_socio, other.id_socio);
	}

	
	
	
}
