package com.safetripbackend.subscription.domain.dto;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class searchDto {

    private int id;
    private String name;
    private LocalDate iniDate;
    private LocalDate endDate;
    private String city;

    public void setId(int id){ this.id=id; }

    public void setUserName(String name){ this.name = name; }

    public void setIniDate(LocalDate iniDate) {
        this.iniDate = iniDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public void setCity(String city) {this.city = city;}


}
