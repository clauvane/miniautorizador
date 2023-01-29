package com.clauvane.autorizador.controller;

import com.clauvane.autorizador.exception.ResourceNotFoundException;
import com.clauvane.autorizador.model.dto.CartaoDto;
import com.clauvane.autorizador.model.entity.Cartao;
import com.clauvane.autorizador.service.CartaoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/cartoes")
public class CartaoController {

    public static final String CARTAO_INEXISTENTE = "CARTAO_INEXISTENTE";

    @Autowired
    private CartaoService cartaoService;

    @PostMapping
    public ResponseEntity<Object> salvar(@RequestBody @Valid CartaoDto cartaoDto){
        Optional<Cartao> cartaoDb = cartaoService.findByNumeroCartao(cartaoDto.getNumeroCartao());
        if (cartaoDb.isPresent()) {
            return status(HttpStatus.UNPROCESSABLE_ENTITY).body(cartaoDto);
        }

        Cartao cartao = new Cartao();
        BeanUtils.copyProperties(cartaoDto, cartao);
        cartao.setSaldo(new BigDecimal(500));
        cartaoService.saveOrUpdate(cartao);
        return status(HttpStatus.CREATED).body(cartaoDto);
    }

    @GetMapping
    public ResponseEntity<List<Cartao>> getAll(){
        return status(HttpStatus.OK).body(cartaoService.findAll());
    }

    @GetMapping("/{numeroCartao}")
    public ResponseEntity<Object> consultarSaldo(@PathVariable(value = "numeroCartao") String numeroCartao){
        Optional<Cartao> cartaoDb = cartaoService.findByNumeroCartao(numeroCartao);
        if (!cartaoDb.isPresent()) {
            return status(HttpStatus.NOT_FOUND).body("");
        }
        return status(HttpStatus.OK).body(cartaoDb.get().getSaldo());
    }

}
