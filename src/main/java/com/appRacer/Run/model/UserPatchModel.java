package com.appRacer.Run.model;

import java.util.UUID;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserPatchModel {

	@Size(min = 10, max = 64, message = "name: size must be between 10 and 64")
	private String name;
	@Schema(example = "111.222.333-04", accessMode = Schema.AccessMode.READ_ONLY)
	@Size(min = 11, max = 15, message = "CPF: size must be between 11 and 15")
	private String cpf;
	@Min(value = 4, message = "age: must be greater than or equal to 4")
	@Max(value = 130, message = "age: must be less than or equal to 130 ")
	private Integer age;
	@Min(value = 1, message = "height: must be greater than or equal to 1")
	@Max(value = 3, message = "height: must be less than or equal to 3")
	private Float height;
	@Min(value = 20, message = "weight: must be greater than or equal to 20")
	@Max(value = 300, message = "weight: must be less than or equal to 300")
	private Float weight;
}
