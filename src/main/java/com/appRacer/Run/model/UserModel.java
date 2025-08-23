package com.appRacer.Run.model;

import java.util.UUID;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
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
	@Schema(accessMode = Schema.AccessMode.READ_ONLY)
	private UUID userId;
	@Column
	@NotBlank(message = "mandatory name field")
	private String name;
	@Column(unique = true)
	@NotBlank(message = "mandatory cpf field")
	private String cpf;
	@Column
	@NotNull(message = "mandatory age field")
	private Integer age;
	@Column
	@NotNull(message = "mandatory height field")
	private Float height;
	@Column
	@NotNull(message = "mandatory peso weight field")
	private Float weight;
	@Column
	@NotNull(message = "mandatory imc field")
	@Schema(accessMode = Schema.AccessMode.READ_ONLY)
	private Float imc;

	public UserModel(String name, String cpf, Integer age, Float height, Float weight, Float imc) {
	    this.name = name;
	    this.cpf = cpf;
	    this.age = age;
	    this.height = height;
	    this.weight = weight;
	    this.imc = imc;
	}
}
