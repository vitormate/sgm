package com.rokaly.sgm.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record MachineRequest(
        @NotBlank
        String serial,
        @NotBlank
        String type,
        @NotBlank
        String brand,
        @NotBlank
        String model,
        @NotNull
        Double hourMeter) {
    public MachineRequest(MachineRequest data) {
        this(data.serial(), data.type(), data.brand(), data.model(), data.hourMeter());
    }
}
