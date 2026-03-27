package com.rokaly.sgm.model;

import com.rokaly.sgm.exception.BusinessRuleException;
import com.rokaly.sgm.utils.enums.Status;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "machines")
public class Machine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(unique = true, length = 50, nullable = false)
    private String serial;

    @NotBlank
    @Column(length = 100, nullable = false)
    private String type;

    @NotBlank
    @Column(length = 50, nullable = false)
    private String brand;

    @NotBlank
    @Column(length = 50, nullable = false)
    private String model;

    @NotNull
    @Column(nullable = false)
    private Double hourMeter;

    @NotNull
    @Column(length = 20, nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToMany(mappedBy = "machine", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Maintenance> maintenance;


    public Machine(String serial, String type, String brand, String model, Double hourMeter) {
        this.serial = serial;
        this.type = type;
        this.brand = brand;
        this.model = model;
        this.hourMeter = hourMeter;
        this.status = Status.ATIVA;
    }

    public Machine() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Double getHourMeter() {
        return hourMeter;
    }

    public void setHourMeter(Double hourMeter) {
        this.hourMeter = hourMeter;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<Maintenance> getMaintenance() {
        return maintenance;
    }

    public void setMaintenance(List<Maintenance> maintenance) {
        this.maintenance = maintenance;
    }

    public void inactivate() {
        this.status = Status.INATIVA;
    }

    public void startMaintenance(Double hourMeter, LocalDateTime dateTime) {
        validNotInMaintenance();
        validHourMeter(hourMeter);
        validDateTime(dateTime);
        this.status = Status.MANUTENCAO;
    }

    public void activate() {
        validNotInActive();
        this.status = Status.ATIVA;
    }

    public void updateData(String type, String brand, String model, Double hourMeter) {
        if (type != null) this.type = type;
        if (brand != null) this.brand = brand;
        if (model != null) this.model = model;
        if (hourMeter != null) {
            validHourMeter(hourMeter);
        }
    }

    private void validHourMeter(Double hourMeter) {
        if (hourMeter < this.hourMeter) {
            throw new BusinessRuleException("Hour meter can't be lower than current value!");
        }

        this.hourMeter = hourMeter;
    }

    private void validDateTime(LocalDateTime dateTime) {
        if (dateTime.isAfter(LocalDateTime.now())) {
            throw new BusinessRuleException("Datetime can't be in the future!");
        }
    }

    private void validNotInMaintenance() {
        if (this.status == Status.MANUTENCAO) {
            throw new BusinessRuleException("Machine already in maintenance!");
        }
    }

    private void validNotInActive() {
        if (this.status == Status.ATIVA) {
            throw new BusinessRuleException("Machine already ACTIVE!");
        }
    }
}
