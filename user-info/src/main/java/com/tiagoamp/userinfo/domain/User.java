package com.tiagoamp.userinfo.domain;

import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor  @AllArgsConstructor
public class User {

    private Long id;
    private String name;
    private String email;
    private LocalDate birthdate;
    private String address;

}
