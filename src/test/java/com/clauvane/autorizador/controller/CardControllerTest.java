package com.clauvane.autorizador.controller;

import com.clauvane.autorizador.mock.CardMock;
import com.clauvane.autorizador.model.entity.Card;
import com.clauvane.autorizador.service.CardService;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CardController.class)
class CardControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CardService cardService;

    private ObjectMapper mapper;

    @BeforeEach
    void setup() {
        mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
    }

    @Test
    void shouldGetCardById() throws Exception {
        when(cardService.findById(1L)).thenReturn(Optional.of(new Card()));
        this.mockMvc.perform(
                get("/autorizador/card/1")
                        .contentType("application/json")
        ).andExpect(status().isOk());
    }

    @Test
    void shouldGetAllCards() throws Exception {
        when(cardService.findAll()).thenReturn(List.of(CardMock.getSample()));
        this.mockMvc.perform(
                get("/autorizador/card")
                        .contentType("application/json")
        ).andExpect(status().isOk());
    }

    @Test
    void shouldSaveWithSuccess() throws Exception {
        this.mockMvc.perform(
                post("/autorizador/card")
                        .contentType("application/json")
                        .content(mapper.writeValueAsString(CardMock.getSample2()))
        ).andExpect(status().isCreated());
    }

    @Test
    void shouldUpateWithSuccess() throws Exception {
        when(cardService.findById(1L)).thenReturn(Optional.of(new Card()));
        this.mockMvc.perform(
                put("/autorizador/card/" + 1L)
                        .contentType("application/json")
                        .content(mapper.writeValueAsString(CardMock.getSample()))
        ).andExpect(status().isOk());
    }

    @Test
    void shouldNotUpateWhenNotFound() throws Exception {
        this.mockMvc.perform(
                put("/autorizador/card/1")
                        .contentType("application/json")
                        .content(mapper.writeValueAsString(CardMock.getSample2()))
        ).andExpect(status().isNotFound());
    }

    @Test
    void shouldDeleteWithSuccess() throws Exception {
        when(cardService.findById(1L)).thenReturn(Optional.of(new Card()));
        this.mockMvc.perform(
                delete("/autorizador/card/" + 1L)
                        .contentType("application/json")
        ).andExpect(status().isOk());
    }

    @Test
    void shouldNotDeleteWhenNotFound() throws Exception {
        this.mockMvc.perform(
                delete("/autorizador/card/1")
                        .contentType("application/json")
        ).andExpect(status().isNotFound());
    }

}