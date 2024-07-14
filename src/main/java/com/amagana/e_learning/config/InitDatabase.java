package com.amagana.e_learning.config;

import com.amagana.e_learning.dto.*;
import com.amagana.e_learning.entity.Course;
import com.amagana.e_learning.service.AuthorService;
import com.amagana.e_learning.service.CourseService;
import com.amagana.e_learning.service.PublishCourseService;
import net.datafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

@Configuration
public class InitDatabase {

    @Bean
    CommandLineRunner runner(AuthorService authorService, CourseService courseService,
                             PublishCourseService publishCourseService) {
        return args -> {
            // Sample data
            for (int i = 0; i < 200; i++) {
                Faker faker = new Faker();
                var name = faker.name().firstName();
                AuthorDtoRequest authorDtoRequest = AuthorDtoRequest.builder()
                        .bornAt(LocalDateTime.of(faker.number().numberBetween(1980, 2020),
                                faker.number().numberBetween(1, 12),
                                faker.number().numberBetween(1, 28),
                                faker.number().numberBetween(1, 23),
                                faker.number().numberBetween(1, 59),
                                faker.number().numberBetween(1, 59)))
                        .firstName(name)
                        .lastName(faker.name().lastName())
                        .age(faker.number().numberBetween(0, 100))
                        .email(i%2==0? name+"@gmail.com" : name+"@yahoo.fr")
                        .createdAt(LocalDateTime.now())
                        .lastUpdatedAt(LocalDateTime.now())
                        .build();
                CourseDtoRequest courseDtoRequest = CourseDtoRequest.builder()
                        .title(faker.book().title())
                        .description(faker.book().genre())
                        .createdAt(LocalDateTime.now())
                        .lastUpdatedAt(LocalDateTime.now())
                        .build();
               AuthorDtoResponse authorDtoResponse = authorService.createAuthor(authorDtoRequest);
               CourseDtoResponse courseDtoResponse = courseService.createCourse(courseDtoRequest);
                PublishCourseDtoRequest publishCourseDtoRequest = PublishCourseDtoRequest.builder()
                        .courseId(courseDtoResponse.id())
                        .authorId(authorDtoResponse.id())
                        .publishDate(LocalDateTime.of(faker.number().numberBetween(1980, 2020),
                                faker.number().numberBetween(1, 12),
                                faker.number().numberBetween(1, 28),
                                faker.number().numberBetween(1, 23),
                                faker.number().numberBetween(1, 59),
                                faker.number().numberBetween(1, 59)))
                        .comment(faker.text().text(50))
                        .build();
                publishCourseService.publishCourse(publishCourseDtoRequest);
            }
            /*authorRepository.updateAuthor(25, 2);
            authorRepository.updateByNameQuery(30);
            authorRepository.findByAgeUp(30).forEach(System.out::println);
            Specification<Author> spec = Specification
                          .where(AuthorSpecification.hasAge(22))
                    .or(AuthorSpecification.emailContains("yahoo.com"))
                    .and(AuthorSpecification.firstnameLike("Trudie"));
            authorRepository.findAll(spec).forEach(System.out::println);*/
        };
    }
}
