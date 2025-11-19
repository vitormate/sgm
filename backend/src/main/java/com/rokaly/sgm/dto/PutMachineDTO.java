package com.rokaly.sgm.dto;

import jakarta.validation.constraints.NotNull;

public record PutMachineDTO(
        @NotNull
        Long id,
        String type,
        String brand,
        String model,
        Double hourMeter) {
}
