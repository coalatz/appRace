package com.appRacer.Run.model;

import java.util.UUID;

import jakarta.persistence.Access;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor 
@Getter
@Setter
@Entity
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Setter(AccessLevel.NONE)
	private UUID idUser;
	@Column(nullable = false)
	@NotNull(message = "campo nome completo obrigatorio")
	private String nomeCompleto;
	@Column(nullable = false, unique = true)
	@NotNull(message = "campo cpf obrigatorio")
	private String cpf;
	@Column(nullable = false)
	@NotNull(message = "campo idade obrigatorio")
	private int idade;
	@Column(nullable = false)
	@NotNull(message = "campo altura obrigatorio")
	private float alturaCm;
	@Column(nullable = false)
	@NotNull(message = "campo peso obrigatorio")
	private float pesoKg;
	@Column(nullable = false)
	@NotNull(message = "campo imc obrigatorio")
	private float imc;
	
	public Usuario(String nome, String cpf, int idade, float altura, float peso, float imc) {
		this.nomeCompleto = nome;
		this.cpf = cpf;
		this.idade = idade;
		this.alturaCm = altura;
		this.pesoKg = peso;
		this.imc = imc;
	}
	
	
	
}
