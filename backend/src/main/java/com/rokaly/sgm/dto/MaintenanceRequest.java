package com.rokaly.sgm.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record MaintenanceRequest(
        @NotNull
        LocalDateTime dateTime,
        @NotBlank
        String description,
        @NotNull
        Double hourMeter,
        @NotNull
        Long idMachine
) {
    public MaintenanceRequest(MaintenanceRequest data) {
        this(data.dateTime, data.description(), data.hourMeter(), data.idMachine());
    }
}
