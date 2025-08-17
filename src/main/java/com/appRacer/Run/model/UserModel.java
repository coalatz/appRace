package com.appRacer.Run.model;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Entity
public class UserModel {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Setter(AccessLevel.NONE)
	private UUID userId;
	@Column(nullable = false)
	@NotNull(message = "mandatory name field")
	private String name;
	@Column(nullable = false, unique = true)
	@NotNull(message = "mandatory cpf field")
	private String cpf;
	@Column(nullable = false)
	@NotNull(message = "mandatory age field")
	private int age;
	@Column(nullable = false)
	@NotNull(message = "mandatory height field")
	private float height;
	@Column(nullable = false)
	@NotNull(message = "mandatory peso weight field")
	private float weight;
	@Column(nullable = false)
	@NotNull(message = "mandatory imc field")
	private float imc;

	public UserModel(String name, String cpf, int age, float height, float weight, float imc) {
		this.name = name;
		this.cpf = cpf;
		this.age = age;
		this.height = height;
		this.weight = weight;
		this.imc = imc;
	}

	public UUID getUserId() {
		return userId;
	}

	public void setUserId(UUID userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
	}

	public float getWeight() {
		return weight;
	}

	public void setWeight(float weight) {
		this.weight = weight;
	}

	public float getImc() {
		return imc;
	}

	public void setImc(float imc) {
		this.imc = imc;
	}
}
