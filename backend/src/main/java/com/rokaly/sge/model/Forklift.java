package com.rokaly.sge.model;

import com.rokaly.sge.dto.ForkliftDTO;
import com.rokaly.sge.dto.PutForkliftDTO;
import jakarta.persistence.*;

import java.util.List;
// REMOVER
@Entity
@Table(name = "forklifts")
public class Forklift {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String series;
    @Column(unique = true)
    private String internalCode;
    private Double hourMeter;
    @Enumerated(EnumType.STRING)
    private Type type;
    @Enumerated(EnumType.STRING)
    private Status status;
    @OneToMany(mappedBy = "forklift", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<History> history;

    public Forklift(ForkliftDTO data) {
        this.series = data.series();
        this.internalCode = data.internalCode();
        this.hourMeter = data.hourMeter();
        this.type = data.type();
        this.status = data.status();
    }

    public Forklift() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public String getInternalCode() {
        return internalCode;
    }

    public void setInternalCode(String internalCode) {
        this.internalCode = internalCode;
    }

    public Double getHourMeter() {
        return hourMeter;
    }

    public void setHourMeter(Double hourMeter) {
        this.hourMeter = hourMeter;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<History> getHistory() {
        return history;
    }

    public void setHistory(List<History> history) {
        this.history = history;
    }

    public void updateData(PutForkliftDTO data) {
        if (data.hourMeter() != null) {
            this.hourMeter = data.hourMeter();
        }
        if (data.type() != null) {
            this.type = data.type();
        }
    }

    public void deleteForklift() {
        this.status = Status.INATIVA;
    }

    public void activate() {
        this.status = Status.ATIVA;
    }

    public void maintenance() {
        this.status = Status.MANUTENCAO;
    }
}
