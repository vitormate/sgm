package com.rokaly.sge.dto;

import com.rokaly.sge.model.Machine;
import com.rokaly.sge.model.Status;

public record GetMachineDTO(Long id, String serial, String type, String brand, String model, Double hourMeter, Status status) {
    public GetMachineDTO(Machine data) {
        this(data.getId(), data.getSerial(), data.getType(), data.getBrand(), data.getModel(), data.getHourMeter(), data.getStatus());
    }
}
