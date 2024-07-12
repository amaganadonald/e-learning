package com.amagana.e_learning.entity;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Author {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
}
