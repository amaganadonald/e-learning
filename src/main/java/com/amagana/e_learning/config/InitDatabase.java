package com.amagana.e_learning.config;

import com.amagana.e_learning.entity.Author;
import com.amagana.e_learning.repository.AuthorRepository;
import net.datafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;

@Configuration
public class InitDatabase {

    //@Bean
    CommandLineRunner runner(AuthorRepository authorRepository) {
        return args -> {
            // Sample data
            for (int i = 0; i < 200; i++) {
                Faker faker = new Faker();
                var name = faker.name().firstName();
                /*Author author = Author.builder()
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
                        .createdBy("donald")
                        .lastUpdatedBy("donald")
                        .build();
                authorRepository.save(author);*/
            }
            /*authorRepository.updateAuthor(25, 2);
            authorRepository.updateByNameQuery(30);
            authorRepository.findByAgeUp(30).forEach(System.out::println);*/
            Specification<Author> spec = Specification
                          .where(AuthorSpecification.hasAge(22))
                    .or(AuthorSpecification.emailContains("yahoo.com"))
                    .and(AuthorSpecification.firstnameLike("Trudie"));
            authorRepository.findAll(spec).forEach(System.out::println);
        };
    }
}
