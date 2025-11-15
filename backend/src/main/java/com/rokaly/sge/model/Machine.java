package com.rokaly.sge.model;

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


}
