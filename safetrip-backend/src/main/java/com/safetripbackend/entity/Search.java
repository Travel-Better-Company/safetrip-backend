package com.safetripbackend.subscription.domain.entity;
import jakarta.persistence.*;

import lombok.Getter;

import java.time.LocalDate;

@Getter
@Entity
public class search {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private LocalDate iniDate;

    @Column(nullable = false)
    private LocalDate endDate;

    @Column(nullable = false)
    private String city;

    public void setId(int id) {
        this.id = id;
    }

    public void setUserName(String name) {
        this.name = name;
    }

    public void setIniDate(LocalDate iniDate) {
        this.iniDate = iniDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
}
