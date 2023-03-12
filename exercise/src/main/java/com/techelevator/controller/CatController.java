package com.techelevator.controller;

import com.techelevator.dao.CatCardDao;
import com.techelevator.model.CatCard;
import com.techelevator.services.CatFactService;
import com.techelevator.services.CatPicService;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.server.ResponseStatusException;


import javax.validation.Valid;
import java.util.List;

@RestController
public class CatController {


    private CatCardDao catCardDao;
    private CatFactService catFactService;
    private CatPicService catPicService;

    public CatController(CatCardDao catCardDao, CatFactService catFactService, CatPicService catPicService) {
        this.catCardDao = catCardDao;
        this.catFactService = catFactService;
        this.catPicService = catPicService;
    }


    @RequestMapping(path = "/api/cards", method = RequestMethod.GET)
    public List<CatCard> getUserCollection() {
        return catCardDao.list();
    }

    @RequestMapping(path = "/api/cards/{id}", method = RequestMethod.GET)
    public CatCard getCardById(long id) {
        CatCard catCard = catCardDao.get(id);

        return catCardDao.get(id);
    }


    @RequestMapping(path = "/api/cards/random", method = RequestMethod.GET)
    public CatCard getRandom() {
        CatCard newCatCard = new CatCard();
        newCatCard.setImgUrl(catPicService.getPic().getFile());
        newCatCard.setCaption("");
        newCatCard.setCatFact(catFactService.getFact().getText());

        return newCatCard;
    }

    @RequestMapping(path = "/api/cards", method = RequestMethod.POST)
    public CatCard saveCard (@RequestBody CatCard cardToSave) {
        catCardDao.save(cardToSave);
        return cardToSave;
    }

    @RequestMapping(path = "/api/cards/{id}", method = RequestMethod.PUT)
    public CatCard updateCard (@RequestBody CatCard cardToUpdate) {
        catCardDao.update(cardToUpdate.getCatCardId(), cardToUpdate);
        return cardToUpdate;
    }

    @RequestMapping(path="/api/cards", method=RequestMethod.DELETE)
    public void deleteFromCollection(@RequestBody CatCard cardToDelete) {
        catCardDao.delete(cardToDelete.getCatCardId());
    }



}
