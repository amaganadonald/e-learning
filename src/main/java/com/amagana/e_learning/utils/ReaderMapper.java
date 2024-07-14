package com.amagana.e_learning.utils;

import com.amagana.e_learning.dto.ReaderDtoRequest;
import com.amagana.e_learning.dto.ReaderDtoResponse;
import com.amagana.e_learning.entity.Reader;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ReaderMapper {
    ReaderMapper instance = Mappers.getMapper(ReaderMapper.class);

    Reader readerDtoRequestToReader(ReaderDtoRequest readerDtoRequest);

    @InheritInverseConfiguration
    ReaderDtoResponse readerToReaderDtoResponse(Reader reader);
}
