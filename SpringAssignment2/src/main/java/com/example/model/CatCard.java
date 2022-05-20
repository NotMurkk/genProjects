package com.example.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class CatCard {

    public Long catCardId;
    @NotNull
    @JsonProperty("fact")
    public CatFact catFact;
    @NotNull
    public CatPic imgUrl;
    @NotEmpty
    public String caption;

    public CatCard() {

    }

    public CatCard(Long id, CatFact fact, CatPic pic, String caption) {
        this.catCardId = id;
        this.catFact = fact;
        this.imgUrl = pic;
        this.caption = caption;
    }

    public Long getCatCardId() {
        return catCardId;
    }
    public void setCatCardId(Long catCardId) {
        this.catCardId = catCardId;
    }

    public String getCatFact() {
        return catFact.getText();
    }
    public void setCatFact(String catFact) {
        this.catFact = new CatFact(catFact);
    }

    public String getImgUrl() {
        return imgUrl.getFile();
    }
    public void setImgUrl(String imgUrl) {
        this.imgUrl = new CatPic(imgUrl);
    }

    public String getCaption() {
        return caption;
    }
    public void setCaption(String caption) {
        this.caption = caption;
    }

}

