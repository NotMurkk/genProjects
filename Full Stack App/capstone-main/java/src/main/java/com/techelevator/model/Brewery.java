package com.techelevator.model;

import javax.validation.constraints.NotNull;

public class Brewery {

    private Long breweryId;
    @NotNull
    private Long ownerId;
    @NotNull
    private String name;
    @NotNull
    private String address;
    @NotNull
    private String description;

    public Brewery() {
    }

    public Brewery(Long breweryId, Long ownerId, String name, String address, String description) {
        this.breweryId = breweryId;
        this.ownerId = ownerId;
        this.name = name;
        this.address = address;
        this.description = description;
    }

    public Long getBreweryId() {
        return breweryId;
    }

    public void setBreweryId(Long breweryId) {
        this.breweryId = breweryId;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
