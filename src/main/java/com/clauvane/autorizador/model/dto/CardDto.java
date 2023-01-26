package com.clauvane.autorizador.model.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Data
public class CardDto {

    @NotBlank(message = "Number is required.")
    @Size(min = 20, max = 20, message = "The length of card number is 20.")
    private String number;

    @NotBlank(message = "Password is required.")
    @Size(min = 8, max = 20, message = "The password would be between 8 and 20 character.")
    private String password;

    @NotNull(message = "Balance is required.")
    private BigDecimal balance;

}
