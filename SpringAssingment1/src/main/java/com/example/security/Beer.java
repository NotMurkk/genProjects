package com.example.security;

public class Beer {

    private Long beerId;
    private Long breweryId;
    private String name;
    private String type;
    private Double abv;
    private String description;
    private String image;

    public Beer() {
    }

    public Beer(Long beerId, Long breweryId, String name, String type, Double abv, String description, String image) {
        this.beerId = beerId;
        this.breweryId = breweryId;
        this.name = name;
        this.type = type;
        this.abv = abv;
        this.description = description;
        this.image = image;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}

