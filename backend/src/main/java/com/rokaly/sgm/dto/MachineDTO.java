package com.rokaly.sgm.dto;

import com.rokaly.sgm.utils.enums.Status;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record MachineDTO(
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
    public MachineDTO(MachineDTO data) {
        this(data.serial(), data.type(), data.brand(), data.model(), data.hourMeter());
    }
}
