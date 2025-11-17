package com.rokaly.sge.dto;

import com.rokaly.sge.model.Maintenance;
import com.rokaly.sge.model.Status;

import java.time.LocalDateTime;

public record GetMaintenanceDTO(Long id, LocalDateTime dateTime, String description, Double hourMeter, String serial,
                                String type, String brand, String model, Status status

) {
    public GetMaintenanceDTO(Maintenance m) {
        this(m.getId(), m.getDateTime(), m.getDescription(), m.getHourMeter(), m.getMachine().getSerial(), m.getMachine().getType(), m.getMachine().getBrand(), m.getMachine().getModel(), m.getMachine().getStatus());
    }
}
