package com.rokaly.sgm.dto;

import com.rokaly.sgm.model.Maintenance;

import java.time.LocalDateTime;

public record GetMaintenanceDTO(Long id, LocalDateTime dateTime, String description, Double hourMeter, String serial,
                                String type, String brand, String model

) {
    public GetMaintenanceDTO(Maintenance m) {
        this(m.getId(), m.getDateTime(), m.getDescription(), m.getHourMeter(), m.getMachine().getSerial(), m.getMachine().getType(), m.getMachine().getBrand(), m.getMachine().getModel());
    }
}
