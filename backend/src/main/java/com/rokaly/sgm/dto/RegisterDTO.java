package com.rokaly.sgm.dto;

import com.rokaly.sgm.model.RoleUser;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RegisterDTO(
        @NotBlank
        String login,
        @NotBlank
        String password,
        @NotNull
        RoleUser role
) {
}
