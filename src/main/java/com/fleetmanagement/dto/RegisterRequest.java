package com.fleetmanagement.dto;

import com.fleetmanagement.utils.Role;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * DTO for user registration.
 */
@Data
public class RegisterRequest {

	@NotBlank(message = "Name is required")
	@Schema(description = "Full name of the user", example = "John Doe")
	private String name;

	@NotBlank(message = "Email is required")
	@Email(message = "Invalid email format")
	@Schema(description = "Email address used for login", example = "john.doe@example.com")
	private String email;

	@NotBlank(message = "Password is required")
	@Size(min = 6, message = "Password must be at least 6 characters")
	@Schema(description = "Password for the user account (min 6 characters)", example = "strongPassword123")
	private String password;

	@NotNull(message = "Role is required")
	@Schema(description = "Role of the user in the system", example = "DRIVER")
	private Role role;
}
