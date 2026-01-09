package com.rokaly.sgm.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "maintenance")
public class Maintenance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime dateTime;
    @Column(length = 500)
    private String description;
    private Double hourMeter;
    @ManyToOne
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
