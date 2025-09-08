package com.appRacer.Run.model;

import java.util.UUID;

import org.hibernate.validator.constraints.br.CPF;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
	@Size(min = 5, max = 64, message = "name: size must be between 5 and 64")
	@Schema(example = "Eduardo Alves")
	private String name;
	@Column(unique = true)
	@NotBlank(message = "mandatory cpf field")
	@Schema(example = "111.222.333-04")
	@Size(min = 11, max = 15, message = "CPF: size must be between 11 and 15")
	private String cpf;
	@Column
	@NotNull(message = "mandatory age field")
	@Schema(example = "20")
	@Min(value = 4, message = "age: must be greater than or equal to 4")
	@Max(value = 130, message = "age: must be less than or equal to 130 ")
	private Integer age;
	@Column
	@NotNull(message = "mandatory height field")
	@Min(value = 1, message = "height: must be greater than or equal to 1")
	@Max(value = 3, message = "height: must be less than or equal to 3")
	@Schema(example = "1.80")
	private Float height;
	@Column
	@NotNull(message = "mandatory peso weight field")
	@Schema(example = "90")
	@Min(value = 20, message = "weight: must be greater than or equal to 20")
	@Max(value = 300, message = "weight: must be less than or equal to 300")
	private Float weight;
	@Column
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
