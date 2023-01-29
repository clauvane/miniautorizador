package com.clauvane.autorizador.service;

import com.clauvane.autorizador.model.entity.Cartao;
import com.clauvane.autorizador.repository.CartaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class CartaoService {

    @Autowired
    private CartaoRepository cartaoRepository;

    public Cartao saveOrUpdate(Cartao cartao) {
        return cartaoRepository.save(cartao);
    }

    public List<Cartao> findAll() {
        return cartaoRepository.findAll();
    }

    public Optional<Cartao> findById(Long id) {
        return cartaoRepository.findById(id);
    }

    public Optional<Cartao> findByNumeroCartao(String numeroCartao) {
        return cartaoRepository.findByNumeroCartao(numeroCartao);
    }

    public Optional<Cartao> findByNumeroCartaoWithPessimisticLock(String numeroCartao) {
        return cartaoRepository.findByNumeroCartaoWithPessimisticLock(numeroCartao);
    }

}
