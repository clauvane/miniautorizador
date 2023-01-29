package com.clauvane.autorizador.controller;

import com.clauvane.autorizador.mock.CartaoMock;
import com.clauvane.autorizador.model.entity.Cartao;
import com.clauvane.autorizador.service.CartaoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CartaoController.class)
class CartaoControllerTest {

    public static final String BASE_URL = "/cartoes";
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
    void deveriaPegarCartaoPeloNumero() throws Exception {
        when(cartaoService.findByNumeroCartao("5544618566894276"))
                .thenReturn(Optional.of(new Cartao()));
        this.mockMvc.perform(get(BASE_URL + "/5544618566894276")
                    .contentType("application/json")
        ).andExpect(status().isOk());
    }

    @Test
    void deveriaPegarTodosCartoes() throws Exception {
        when(cartaoService.findAll()).thenReturn(List.of(CartaoMock.getSample()));
        this.mockMvc.perform(get(BASE_URL)
                    .contentType("application/json")
        ).andExpect(status().isOk());
    }

    @Test
    void deveriaSalvarCartaoComSucesso() throws Exception {
        this.mockMvc.perform(post(BASE_URL)
                    .contentType("application/json")
                    .content(mapper.writeValueAsString(CartaoMock.getSample2()))
        ).andExpect(status().isCreated());
    }

}