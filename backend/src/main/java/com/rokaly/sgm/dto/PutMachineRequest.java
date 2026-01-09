package com.rokaly.sgm.dto;

import jakarta.validation.constraints.NotNull;

public record PutMachineRequest(
        @NotNull
        Long id,
        String type,
        String brand,
        String model,
        Double hourMeter) {
}
