package com.techelevator.model;

import javax.validation.constraints.NotNull;

public class Beer {

    private Long beerId;
    @NotNull
    private Long breweryId;
    private String breweryName;
    @NotNull
    private String name;
    @NotNull
    private String type;
    @NotNull
    private Double abv;
    @NotNull
    private String description;

    public Beer() {
    }

    public Beer(Long beerId, Long breweryId,String breweryName, String name, String type, Double abv, String description) {
        this.beerId = beerId;
        this.breweryId = breweryId;
        this.breweryName = breweryName;
        this.name = name;
        this.type = type;
        this.abv = abv;
        this.description = description;
    }

    public Long getBeerId() {
        return beerId;
    }

    public void setBeerId(Long beerId) {
        this.beerId = beerId;
    }

    public Long getBreweryId() {
        return breweryId;
    }

    public void setBreweryId(Long breweryId) {
        this.breweryId = breweryId;
    }

    public String getBreweryName() {
        return breweryName;
    }

    public void setBreweryName(String breweryName) {
        this.breweryName = breweryName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getAbv() {
        return abv;
    }

    public void setAbv(Double abv) {
        this.abv = abv;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
