package com.rokaly.sgm.model;

import com.rokaly.sgm.dto.MachineDTO;
import com.rokaly.sgm.dto.PutMachineDTO;
import com.rokaly.sgm.utils.enums.Status;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "machines")
public class Machine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String serial;
    private String type;
    private String brand;
    private String model;
    private Double hourMeter;
    @Enumerated(EnumType.STRING)
    private Status status;
    @OneToMany(mappedBy = "machine", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Maintenance> maintenance;


    public Machine(MachineDTO data) {
        this.serial = data.serial();
        this.type = data.type();
        this.brand = data.brand();
        this.model = data.model();
        this.hourMeter = data.hourMeter();
        this.status = data.status();
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

    public void updateData(PutMachineDTO data) {
        if (data.type() != null) {
            this.type = data.type();
        }

        if (data.brand() != null) {
            this.brand = data.brand();
        }

        if (data.model() != null) {
            this.model = data.model();
        }

        if (data.hourMeter() != null) {
            this.hourMeter = data.hourMeter();
        }
    }

    public void deleteForklift() {
        this.status = Status.INATIVA;
    }

    public void maintenance() {
        this.status = Status.MANUTENCAO;
    }

    public void activate() {
        this.status = Status.ATIVA;
    }
}
