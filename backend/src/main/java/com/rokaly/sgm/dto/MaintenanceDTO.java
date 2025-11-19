package com.rokaly.sgm.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record MaintenanceDTO(
        @NotNull
        LocalDateTime dateTime,
        @NotBlank
        String description,
        @NotNull
        Double hourMeter,
        @NotNull
        Long idMachine
) {
    public MaintenanceDTO(MaintenanceDTO data) {
        this(data.dateTime, data.description(), data.hourMeter(), data.idMachine());
    }
}
