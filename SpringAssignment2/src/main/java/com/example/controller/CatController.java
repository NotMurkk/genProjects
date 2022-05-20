package com.example.controller;

import com.example.dao.CatCardDao;
import com.example.model.CatCard;
import com.example.model.CatCardNotFoundException;
import com.example.services.CatFactService;
import com.example.services.CatPicService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/api/cards")
public class CatController {

    private CatCardDao catCardDao;
    private CatFactService catFactService;
    private CatPicService catPicService;

    public CatController(CatCardDao catCardDao, CatFactService catFactService, CatPicService catPicService) {
        this.catCardDao = catCardDao;
        this.catFactService = catFactService;
        this.catPicService = catPicService;
    }

    @RequestMapping(path = "/random", method = RequestMethod.GET)
    public CatCard getRandom() {
        CatCard card = new CatCard();
        card.setCatFact(catFactService.getFact().getText());
        card.setImgUrl(catPicService.getPic().getFile());
        card.setCaption("Random cat card");
        return card;
    }

    @RequestMapping(method = RequestMethod.GET )
    public List<CatCard> list() {
        return catCardDao.list();
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public CatCard getById(@PathVariable long id) throws CatCardNotFoundException {
        return catCardDao.get(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(method = RequestMethod.POST)
    public boolean create(@Valid @RequestBody CatCard catCard) {
        return catCardDao.save(catCard);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.PUT)
    public boolean update(@Valid @RequestBody CatCard catCard, @PathVariable long id) throws CatCardNotFoundException {
        return catCardDao.update(id, catCard);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(path = "{id}", method = RequestMethod.DELETE)
    public boolean delete(@PathVariable long id) throws CatCardNotFoundException {
        return catCardDao.delete(id);
    }
}

