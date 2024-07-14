package com.amagana.e_learning.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Objects;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class Resources extends BaseEntity {

    private String name;
    private int size;
    private String url;
    @OneToOne
    @JoinColumn(name = "resource_id")
    private Reader reader;

}
