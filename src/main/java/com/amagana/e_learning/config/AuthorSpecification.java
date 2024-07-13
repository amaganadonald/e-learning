package com.amagana.e_learning.config;

import com.amagana.e_learning.entity.Author;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import lombok.experimental.UtilityClass;
import org.springframework.data.jpa.domain.Specification;

@UtilityClass
public class AuthorSpecification {
    public Specification<Author> hasAge(int age) {
        return (root, query, cb) -> {
            if (age < 0)
                return null;
            return cb.equal(root.get("age"), age);
        };
    }

    public Specification<Author> emailContains(String email) {
        return (Root<Author> root,CriteriaQuery<?> query, CriteriaBuilder cb) -> {
            if (email == null)
                return null;
            return cb.like(root.get("email"), "%" + email + "%");
        };
    }

    public Specification<Author> firstnameLike(String firstname){
        return (root, query, cb) -> {
            if(firstname == null)
                return null;
            return cb.like(root.get("firstName"), "%" + firstname + "%");
        };
    }
}
