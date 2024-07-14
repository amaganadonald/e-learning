package com.amagana.e_learning.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@SuperBuilder
public class Reader extends BaseEntity {

    private String name;
    private String email;
    private String phone;
    @OneToOne(mappedBy = "reader")
    private Resources resources;
    @ManyToOne
    @JoinColumn(name = "section_id")
    private Section section;
}
