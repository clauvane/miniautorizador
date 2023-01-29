package com.clauvane.autorizador.model.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class CartaoDto {

    @NotBlank(message = "O número do cartão é obrigatório.")
    @Size(min = 20, max = 20, message = "O cartão deve possuir 20 caracteres.")
    private String numeroCartao;

    @NotBlank(message = "A senha é obrigatória.")
    @Size(min = 8, max = 20, message = "A senha deve possuir de 8 a 20 caracteres.")
    private String senha;

}
