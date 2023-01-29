package com.clauvane.autorizador.controller;

import com.clauvane.autorizador.mock.CartaoMock;
import com.clauvane.autorizador.model.dto.CartaoDto;
import com.clauvane.autorizador.model.entity.Cartao;
import com.clauvane.autorizador.service.CartaoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@ExtendWith(SpringExtension.class)
@WebMvcTest(TransacaoController.class)
class TransacaoControllerTest {

    public static final String BASE_URL = "/transacoes";
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CartaoService cartaoService;

    private ObjectMapper mapper;

    @BeforeEach
    void setup() {
        mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
    }

    @Test
    void deveriaDebitarCartao() throws Exception {
        Cartao cartao = CartaoMock.getSample();
        when(cartaoService.findByNumeroCartaoWithPessimisticLock(cartao.getNumeroCartao()))
                .thenReturn(Optional.of(cartao));
        this.mockMvc.perform(post(BASE_URL)
                .contentType("application/json")
                .content(mapper.writeValueAsString(cartao))
        ).andExpect(status().isCreated());
    }

    @Test
    void deveriaRetornarCartaoInexistente() throws Exception {
        this.mockMvc.perform(post(BASE_URL)
                .contentType("application/json")
                .content(mapper.writeValueAsString(CartaoMock.getSample()))
        ).andExpect(content().string(TransacaoController.CARTAO_INEXISTENTE));
    }

    @Test
    void deveriaRetornarSenhaInvalida() throws Exception {
        Cartao cartao = CartaoMock.getSample();
        when(cartaoService.findByNumeroCartaoWithPessimisticLock(cartao.getNumeroCartao()))
                .thenReturn(Optional.of(cartao));
        Cartao cartaoJson = new Cartao();
        BeanUtils.copyProperties(cartao, cartaoJson);
        cartaoJson.setSenha("teste1234");
        this.mockMvc.perform(post(BASE_URL)
                .contentType("application/json")
                .content(mapper.writeValueAsString(cartaoJson))
        ).andExpect(content().string(TransacaoController.SENHA_INVALIDA));
    }

    @Test
    void deveriaRetornarSaldoInvalido() throws Exception {
        Cartao cartao = CartaoMock.getSample();
        when(cartaoService.findByNumeroCartaoWithPessimisticLock(cartao.getNumeroCartao()))
                .thenReturn(Optional.of(cartao));
        Cartao cartaoJson = new Cartao();
        BeanUtils.copyProperties(cartao, cartaoJson);
        cartaoJson.setSaldo(new BigDecimal(501));
        this.mockMvc.perform(post(BASE_URL)
                .contentType("application/json")
                .content(mapper.writeValueAsString(cartaoJson))
        ).andExpect(content().string(TransacaoController.SALDO_INSUFICIENTE));
    }

    @Test
    void deveriaCreditarCartao() throws Exception {
        Cartao cartao = CartaoMock.getSample2();
        when(cartaoService.findByNumeroCartaoWithPessimisticLock(cartao.getNumeroCartao()))
                .thenReturn(Optional.of(cartao));
        this.mockMvc.perform(post(BASE_URL + "/recarga")
                    .contentType("application/json")
                    .content(mapper.writeValueAsString(cartao))
        ).andExpect(status().isCreated());
    }

}