package com.clauvane.autorizador.model.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Data
public class TransacaoDto {

    @NotBlank(message = "O número do cartão é obrigatório.")
    @Size(min = 16, max = 16, message = "O cartão deve possuir 16 caracteres.")
    private String numeroCartao;

    @NotBlank(message = "A senha é obrigatória.")
    @Size(min = 8, max = 20, message = "A senha deve possuir de 8 a 20 caracteres.")
    private String senha;

    @NotNull
    private BigDecimal saldo;

}
