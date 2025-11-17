package com.rokaly.sge.dto;

public record PutMachineDTO(Long id, String type, String brand, String model, Double hourMeter) {
}
