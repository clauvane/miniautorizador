package com.clauvane.autorizador.service;

import com.clauvane.autorizador.mock.CartaoMock;
import com.clauvane.autorizador.model.entity.Cartao;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.transaction.Transactional;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@ExtendWith(SpringExtension.class)
@SpringBootTest
class CartaoServiceTest {

    @Autowired
    private CartaoService cartaoService;

    @Rollback
    @Test
    void saveOrUpdate() {
        Cartao cartao = CartaoMock.getSample();

        Cartao cartaoDb = cartaoService.saveOrUpdate(cartao);

        assertNotNull(cartaoDb.getId());
        assertEquals(cartao.getNumeroCartao(), cartaoDb.getNumeroCartao());
        assertEquals(cartao.getSaldo(), cartaoDb.getSaldo());
        assertEquals(cartao.getSenha(), cartaoDb.getSenha());
    }

    @Rollback
    @Test
    void findAll() {
        cartaoService.saveOrUpdate(CartaoMock.getSample());
        cartaoService.saveOrUpdate(CartaoMock.getSample2());

        List<Cartao> cartoes = cartaoService.findAll();
        assertFalse(cartoes.isEmpty());
        assertEquals(2, cartoes.size());
    }

    @Rollback
    @Test
    void findById() {
        Cartao cartao = CartaoMock.getSample();
        cartaoService.saveOrUpdate(cartao);
        Cartao cartaoDb = cartaoService.findById(cartao.getId()).orElse(null);
        assertNotNull(cartaoDb);
        assertEquals(cartao.getNumeroCartao(), cartaoDb.getNumeroCartao());
        assertEquals(cartao.getSaldo(), cartaoDb.getSaldo());
        assertEquals(cartao.getSenha(), cartaoDb.getSenha());
    }

    @Rollback
    @Test
    void findByNumeroCartao() {
        Cartao cartao = CartaoMock.getSample();
        cartaoService.saveOrUpdate(cartao);
        Cartao cartaoDb = cartaoService.findByNumeroCartao(cartao.getNumeroCartao()).orElse(null);
        assertNotNull(cartaoDb);
        assertEquals(cartao.getNumeroCartao(), cartaoDb.getNumeroCartao());
        assertEquals(cartao.getSaldo(), cartaoDb.getSaldo());
        assertEquals(cartao.getSenha(), cartaoDb.getSenha());
    }

    @Rollback
    @Test
    void findByNumeroCartaoPessimistic() {
        Cartao cartao = CartaoMock.getSample();
        cartaoService.saveOrUpdate(cartao);
        Cartao cartaoDb = cartaoService.findByNumeroCartaoWithPessimisticLock(cartao.getNumeroCartao()).orElse(null);
        assertNotNull(cartaoDb);
        assertEquals(cartao.getNumeroCartao(), cartaoDb.getNumeroCartao());
        assertEquals(cartao.getSaldo(), cartaoDb.getSaldo());
        assertEquals(cartao.getSenha(), cartaoDb.getSenha());
    }

}