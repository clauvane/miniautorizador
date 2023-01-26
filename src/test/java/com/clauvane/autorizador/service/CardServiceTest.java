package com.clauvane.autorizador.service;

import com.clauvane.autorizador.mock.CardMock;
import com.clauvane.autorizador.model.entity.Card;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class CardServiceTest {

    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss");
    @Autowired
    private CardService cardService;

    @Test
    void saveOrUpdate() {
        Card card = CardMock.getSample();

        Card cardDb = cardService.saveOrUpdate(card);

        assertNotNull(cardDb.getId());
        assertEquals(card.getNumber(), cardDb.getNumber());
        assertEquals(card.getBalance(), cardDb.getBalance());
        assertEquals(card.getPassword(), cardDb.getPassword());
        assertEquals(card.getCreatedAt(), cardDb.getCreatedAt());
    }

    @Test
    void delete() {
        Card card = cardService.saveOrUpdate(CardMock.getSample());
        assertDoesNotThrow(() -> cardService.delete(card));
    }

    @Test
    void findAll() {
        cardService.saveOrUpdate(CardMock.getSample());
        cardService.saveOrUpdate(CardMock.getSample2());

        List<Card> cards = cardService.findAll();
        assertFalse(cards.isEmpty());
        assertEquals(2, cards.size());
    }

    @Test
    void findById() {
        Card card = CardMock.getSample();
        cardService.saveOrUpdate(card);
        Card cardDb = cardService.findById(card.getId()).orElse(null);
        assertNotNull(cardDb);
        assertEquals(card.getNumber(), cardDb.getNumber());
        assertEquals(card.getBalance(), cardDb.getBalance());
        assertEquals(card.getPassword(), cardDb.getPassword());
        assertEquals(card.getCreatedAt().format(DATE_TIME_FORMATTER), cardDb.getCreatedAt().format(DATE_TIME_FORMATTER));
    }

}