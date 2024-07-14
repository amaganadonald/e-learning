package com.amagana.e_learning.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@SuperBuilder
@NamedQueries({
        @NamedQuery(name = "Author.findByAgeUp", query = "SELECT a FROM Author a WHERE a.age >= :age"),
        @NamedQuery(name = "Author.updateByNameQuery", query = "UPDATE Author a SET a.age =:age where a.email like '%yahoo.com'")
})
public class Author extends BaseEntity{

    @Column(name = "first_name", length = 60)
    private String firstName;
    private String lastName;
    private String email;
    private int age;
    @Version
    private int version;
    @Column(updatable = false, nullable = false)
    private LocalDateTime bornAt;
    @OneToMany(mappedBy = "author")
    @ToString.Exclude
    private Set<PublishCourse> publishCourse;

}
