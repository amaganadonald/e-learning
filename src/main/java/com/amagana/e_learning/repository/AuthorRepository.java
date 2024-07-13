package com.amagana.e_learning.repository;

import com.amagana.e_learning.entity.Author;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> , JpaSpecificationExecutor<Author> {
   List<Author> findByLastName(String lastName);
   int countAllByAge(int age);
   Optional<Author> findByEmail(String email);

   List<Author> findAllByFirstNameInIgnoreCase(List<String> firstName);

   List<Author> findAllByLastNameStartingWithIgnoreCase(String lastName);
   List<Author> findAllByFirstNameContainingIgnoreCase(String firstName);
   List<Author> findAllByLastNameEndingWithIgnoreCase(String lastName);
   List<Author> findAllByAgeBetween(int startAge, int endAge);
   @Transactional
   @Modifying
   @Query("update Author a set a.age=:age where a.id=:id")
   int updateAuthor(int age, int id);

   @Transactional
   List<Author> findByAgeUp(@Param("age") int age);

   @Transactional
   @Modifying
   void updateByNameQuery(@Param("age") int age);
}
