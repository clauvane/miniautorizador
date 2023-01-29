package com.clauvane.autorizador.mock;

import com.clauvane.autorizador.model.entity.Cartao;

import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;

public class CartaoMock {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static Cartao getSample() {
        Cartao cartao = new Cartao();
        cartao.setNumeroCartao("12345678901234567890");
        cartao.setSaldo(new BigDecimal("500.00"));
        cartao.setSenha("12345678");

        return cartao;
    }

    public static Cartao getSample2() {
        Cartao cartao = new Cartao();
        cartao.setNumeroCartao("12345678901234567890");
        cartao.setSaldo(new BigDecimal("1000.00"));
        cartao.setSenha("123456789");

        return cartao;
    }

}
