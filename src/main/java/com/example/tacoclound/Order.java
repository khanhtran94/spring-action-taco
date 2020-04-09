package com.example.tacoclound;

import lombok.Data;
import org.hibernate.validator.constraints.CreditCardNumber;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
public class Order {
    @NotBlank(message="Name is required")
    private String name;

    @NotBlank(message="Name is required")
    private String street;

    @NotBlank(message="Name is required")
    private String city;

    @NotBlank(message="Name is required")
    private String state;

    @NotBlank(message="Name is required")
    private String zip;

//    @CreditCardNumber(message="Not a valid credit card number")
    @NotBlank(message="Name is required")
    private String ccNumber;

//    @Pattern(regexp="^(0[1-9]|1[0-2])([\\/])([1-9][0-9])$",
//            message="Must be formatted MM/YY")
    @NotBlank(message="Name is required")
    private String ccExpiration;

//    @Digits(integer = 3, fraction = 0, message = "Invalid CVV")
    @NotBlank(message="Name is required")
    private String ccCVV;
}
