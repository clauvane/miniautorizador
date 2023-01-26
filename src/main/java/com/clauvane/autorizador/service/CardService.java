package com.clauvane.autorizador.service;

import com.clauvane.autorizador.model.entity.Card;
import com.clauvane.autorizador.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CardService {

    @Autowired
    private CardRepository cardRepository;

    @Transactional
    public Card saveOrUpdate(Card card) {
        return cardRepository.save(card);
    }

    @Transactional
    public void delete(Card card) {
        cardRepository.delete(card);
    }

    public List<Card> findAll() {
        return cardRepository.findAll();
    }

    public Optional<Card> findById(Long id) {
        return cardRepository.findById(id);
    }

}
