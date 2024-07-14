package com.amagana.e_learning.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "authors_courses", uniqueConstraints = @UniqueConstraint(columnNames = {"author_id", "course_id"}))
@SuperBuilder
public class PublishCourse extends BaseEntity {

    private LocalDateTime publishDate;
    private String comment;
    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private Author author;
    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;
}
