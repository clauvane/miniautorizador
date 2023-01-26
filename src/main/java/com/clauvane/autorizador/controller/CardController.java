package com.clauvane.autorizador.controller;

import com.clauvane.autorizador.exception.ResourceNotFoundException;
import com.clauvane.autorizador.model.dto.CardDto;
import com.clauvane.autorizador.model.entity.Card;
import com.clauvane.autorizador.service.CardService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/autorizador/card")
public class CardController {

    public static final String CARD_NOT_FOUND = "Card not found.";

    @Autowired
    private CardService cardService;

    @PostMapping
    public ResponseEntity<Object> save(@RequestBody @Valid CardDto cardDto){
        Card card = new Card();
        BeanUtils.copyProperties(cardDto, card);
        card.setCreatedAt(LocalDateTime.now(ZoneId.of("UTC")));
        return status(HttpStatus.CREATED).body(cardService.saveOrUpdate(card));
    }

    @GetMapping
    public ResponseEntity<List<Card>> findAll(){
        return status(HttpStatus.OK).body(cardService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable(value = "id") Long id){
        Optional<Card> cardDb = cardService.findById(id);
        if (!cardDb.isPresent()) {
            throw new ResourceNotFoundException(CARD_NOT_FOUND);
        }
        return status(HttpStatus.OK).body(cardDb.get());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable(value = "id") Long id,
                                         @RequestBody @Valid CardDto cardDto){
        Optional<Card> cardDb = cardService.findById(id);
        if (!cardDb.isPresent()) {
            throw new ResourceNotFoundException(CARD_NOT_FOUND);
        }
        Card card = cardDb.get();
        BeanUtils.copyProperties(cardDto, card);
        card.setId(cardDb.get().getId());
        card.setUpdatedAt(LocalDateTime.now(ZoneId.of("UTC")));
        return status(HttpStatus.OK).body(cardService.saveOrUpdate(card));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable(value = "id") Long id){
        Optional<Card> cardDb = cardService.findById(id);
        if (!cardDb.isPresent()) {
            throw new ResourceNotFoundException(CARD_NOT_FOUND);
        }
        cardService.delete(cardDb.get());
        return status(HttpStatus.OK).body("Card deleted successfully.");
    }

}
