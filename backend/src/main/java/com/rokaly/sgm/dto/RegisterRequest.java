package com.rokaly.sgm.dto;

import com.rokaly.sgm.utils.enums.RoleUser;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RegisterRequest(
        @NotBlank
        String login,
        @NotBlank
        String password,
        @NotNull
        RoleUser role
) {
}
