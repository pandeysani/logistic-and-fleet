package com.fleetmanagement.dto;

import com.fleetmanagement.entity.Role;

import lombok.Data;

@Data
public class RegisterRequest {
    private String name;
    private String email;
    private String password;
    private Role role;
}

