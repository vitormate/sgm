package com.rokaly.sgm.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

@Entity
@Table(name = "maintenance")
public class Maintenance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false)
    private LocalDateTime dateTime;

    @NotBlank
    @Column(length = 500, nullable = false)
    private String description;

    @NotNull
    @Column(nullable = false)
    private Double hourMeter;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "machine_id", nullable = false)
    private Machine machine;

    public Maintenance(LocalDateTime dateTime, String description, Double hourMeter, Machine machine) {
        this.dateTime = dateTime;
        this.description = description;
        this.hourMeter = hourMeter;
        this.machine = machine;
    }

    public Maintenance() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getHourMeter() {
        return hourMeter;
    }

    public void setHourMeter(Double hourMeter) {
        this.hourMeter = hourMeter;
    }

    public Machine getMachine() {
        return machine;
    }

    public void setMachine(Machine machine) {
        this.machine = machine;
    }
}
