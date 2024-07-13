package com.amagana.e_learning.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@SuperBuilder
public class Course extends BaseEntity {

    private String title;
    private String description;
    @Version
    private int version;
    @OneToMany(mappedBy = "course")
    @ToString.Exclude
    private Set<PublishCourse> publishCourse;
    @OneToMany(mappedBy = "course")
    @ToString.Exclude
    private List<Section> section = new ArrayList<>();
}
