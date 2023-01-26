package com.clauvane.autorizador.mock;

import com.clauvane.autorizador.model.entity.Card;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class CardMock {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static Card getSample() {
        Card card = new Card();
        card.setNumber("12345678901234567890");
        card.setBalance(new BigDecimal("500.00"));
        card.setPassword("12345678");
        card.setCreatedAt(LocalDateTime.now(ZoneId.of("UTC")));

        return card;
    }

    public static Card getSample2() {
        Card card = new Card();
        card.setNumber("12345678901234567890");
        card.setBalance(new BigDecimal("1000.00"));
        card.setPassword("123456789");
        card.setCreatedAt(LocalDateTime.now(ZoneId.of("UTC")));

        return card;
    }

}
