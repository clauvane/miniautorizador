package com.clauvane.autorizador.controller;

import com.clauvane.autorizador.model.dto.TransacaoDto;
import com.clauvane.autorizador.model.entity.Cartao;
import com.clauvane.autorizador.service.CartaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.Optional;

import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/transacoes")
public class TransacaoController {

    public static final String CARTAO_INEXISTENTE = "CARTAO_INEXISTENTE";
    public static final String SENHA_INVALIDA = "SENHA_INVALIDA";
    public static final String SALDO_INSUFICIENTE = "SALDO_INSUFICIENTE";

    @Autowired
    private CartaoService cartaoService;

    @PostMapping
    public ResponseEntity<Object> efetivarTransacao(@RequestBody @Valid TransacaoDto transacaoDto){
        Optional<Cartao> cartaoDb = cartaoService.findByNumeroCartao(transacaoDto.getNumeroCartao());
        if (!cartaoDb.isPresent()) {
            return status(HttpStatus.UNPROCESSABLE_ENTITY).body(CARTAO_INEXISTENTE);
        }

        Cartao cartao = cartaoDb.get();
        if (!cartao.getSenha().equals(transacaoDto.getSenha())) {
            return status(HttpStatus.UNPROCESSABLE_ENTITY).body(SENHA_INVALIDA);
        }

        if (cartao.getSaldo().compareTo(transacaoDto.getSaldo()) < 0) {
            return status(HttpStatus.UNPROCESSABLE_ENTITY).body(SALDO_INSUFICIENTE);
        }

        cartao.setSaldo(cartao.getSaldo().subtract(transacaoDto.getSaldo()));

        cartaoService.saveOrUpdate(cartao);
        return status(HttpStatus.CREATED).body("OK");
    }

}
