package com.tiagoamp.userinfo.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor  @NoArgsConstructor
@Getter  @Setter
public class UserResponseDTO {

    private Long id;
    private String name;
    private String email;
    private LocalDate birthdate;
    private String address;

}
