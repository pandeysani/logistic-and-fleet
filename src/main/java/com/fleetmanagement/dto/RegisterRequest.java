package com.fleetmanagement.dto;

import com.fleetmanagement.utils.Role;

import lombok.Data;

@Data
public class RegisterRequest {
    private String name;
    private String email;
    private String password;
    private Role role;
}

