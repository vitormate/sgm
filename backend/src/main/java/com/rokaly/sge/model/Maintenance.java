package com.rokaly.sge.model;

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
}
