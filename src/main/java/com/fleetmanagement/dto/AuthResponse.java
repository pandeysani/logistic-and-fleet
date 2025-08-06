package com.fleetmanagement.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Response containing JWT token after successful authentication.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponse {

	@Schema(description = "JWT access token", example = "eyJhbGciOiJIUzI1NiIsInR...")
	private String token;
}
