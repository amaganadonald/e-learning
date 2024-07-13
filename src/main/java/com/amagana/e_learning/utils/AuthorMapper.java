package com.amagana.e_learning.utils;

import com.amagana.e_learning.dto.AuthorDtoRequest;
import com.amagana.e_learning.dto.AuthorDtoResponse;
import com.amagana.e_learning.entity.Author;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AuthorMapper {

    AuthorMapper instance = Mappers.getMapper(AuthorMapper.class);

    Author authorDtoRequestToAuthor(AuthorDtoRequest authorDtoRequest);

    @InheritInverseConfiguration
    AuthorDtoResponse authorToAuthorDtoResponse(Author author);
}
