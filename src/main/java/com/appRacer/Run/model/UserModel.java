package com.appRacer.Run.model;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class UserModel {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Setter(AccessLevel.NONE)
	private UUID userId;
	@Column(nullable = false)
	@NotNull(message = "campo nome completo obrigatorio")
	private String name;
	@Column(nullable = false, unique = true)
	@NotNull(message = "campo cpf obrigatorio")
	private String cpf;
	@Column(nullable = false)
	@NotNull(message = "campo idade obrigatorio")
	private int age;
	@Column(nullable = false)
	@NotNull(message = "campo altura obrigatorio")
	private float height;
	@Column(nullable = false)
	@NotNull(message = "campo peso obrigatorio")
	private float weight;
	@Column(nullable = false)
	@NotNull(message = "campo imc obrigatorio")
	private float imc;

	public UserModel(String name, String cpf, int age, float height, float weight, float imc) {
		this.name = name;
		this.cpf = cpf;
		this.age = age;
		this.height = height;
		this.weight = weight;
		this.imc = imc;
	}



}
