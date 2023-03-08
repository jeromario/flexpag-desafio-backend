package com.flexpag.paymentscheduler.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "agenda")
public class Agenda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "data_criacao",nullable = false)
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate date;
    @ManyToOne
    private User user;
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;


}
