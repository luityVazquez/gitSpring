package com.ejemplos.models.entity;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name="libros")
public class Libro implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@NotEmpty
	private String isbn;
	
	@NotEmpty
	private String autor;
	
	
	private Boolean prestado;
	
	@NotEmpty
	private String titulo;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="id_socio")
	private Socio socio;

	public Libro() {
		this.prestado = false;
	}

	public Libro(@NotNull String isbn, @NotEmpty String autor, @NotEmpty String titulo) {
		super();
		this.isbn = isbn;
		this.autor = autor;
		this.prestado = false;
		this.titulo = titulo;
	}

	public Socio getSocio() {
		return socio;
	}

	public void setSocio(Socio socio) {
		this.socio = socio;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public Boolean getPrestado() {
		return prestado;
	}

	public void setPrestado(Boolean prestado) {
		this.prestado = prestado;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Libro [isbn=" + isbn + ", autor=" + autor + ", prestado=" + prestado + ", titulo=" + titulo + ", socio="
				+ socio + "]";
	}
	
	
}
