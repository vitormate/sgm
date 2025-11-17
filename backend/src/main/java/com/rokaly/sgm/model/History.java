//package com.rokaly.sge.model;
//
//import com.rokaly.sge.dto.HistoryDTO;
//import jakarta.persistence.*;
//// REMOVER
//@Entity
//@Table(name = "history")
//public class History {
//    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//    private Double hourMeter;
//    private String description;
//    private String dateTime;
//    @ManyToOne
//    private Forklift forklift;
//
//    public History(HistoryDTO data, Forklift forklift) {
//        this.hourMeter = data.hourMeter();
//        this.description = data.description();
//        this.dateTime = data.dateTime();
//        this.forklift = forklift;
//    }
//
//    public History() {
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public Double getHourMeter() {
//        return hourMeter;
//    }
//
//    public void setHourMeter(Double hourMeter) {
//        this.hourMeter = hourMeter;
//    }
//
//    public String getDescription() {
//        return description;
//    }
//
//    public void setDescription(String description) {
//        this.description = description;
//    }
//
//    public String getDateTime() {
//        return dateTime;
//    }
//
//    public void setDateTime(String dateTime) {
//        this.dateTime = dateTime;
//    }
//
//    public Forklift getForklift() {
//        return forklift;
//    }
//
//    public void setForklift(Forklift forklift) {
//        this.forklift = forklift;
//    }
//}
