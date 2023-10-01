package com.org.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskDTO {

  /*  @NotBlank(message = "Title cannot be blank")
	@NotNull(message = "ID cannot be null")
	@Positive(message = "ID must be a positive number")
	private Long id;*/

	private Long id;

	@NotBlank
	@NotNull(message = "Title cannot be null")
	@Size(min = 1, max = 255, message = "Title must be between 1 and 255 characters")
	private String title;

    @NotBlank(message = "description cannot be blank")
	@Size(max = 1000, message = "Description must be less than or equal to 1000 characters")
	private String description;


	@NotNull(message = "Completed status cannot be null")
	private Boolean completed;

}
