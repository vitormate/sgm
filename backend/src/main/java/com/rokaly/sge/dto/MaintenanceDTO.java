package com.rokaly.sge.dto;

import java.time.LocalDateTime;

public record MaintenanceDTO(
        LocalDateTime dateTime,
        String description,
        Double hourMeter,
        Long idMachine
) {
    public MaintenanceDTO(MaintenanceDTO data) {
        this(data.dateTime, data.description(), data.hourMeter(), data.idMachine());
    }
}
