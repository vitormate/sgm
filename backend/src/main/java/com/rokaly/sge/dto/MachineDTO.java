package com.rokaly.sge.dto;

import com.rokaly.sge.model.Status;

public record MachineDTO(String serial, String type, String brand, String model, Double hourMeter, Status status) {
    public MachineDTO(MachineDTO data) {
        this(data.serial(), data.type(), data.brand(), data.model(), data.hourMeter(), data.status());
    }
}
