package com.amagana.e_learning.exception.handler;

import com.amagana.e_learning.dto.ErrorsDto;
import com.amagana.e_learning.enums.StatusResponse;
import com.amagana.e_learning.exception.ELearningBusnessException;
import com.amagana.e_learning.exception.EntityNotFoundException;
import com.amagana.e_learning.models.APIResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<APIResponse<List<ErrorsDto>>> validationErrors(MethodArgumentNotValidException exception){
        List<ErrorsDto> errorResponseList = exception.getFieldErrors().stream()
                .map(error -> ErrorsDto.builder()
                        .field(error.getField()).message(error.getDefaultMessage()).build())
                .toList();
        return ResponseEntity.badRequest().body(APIResponse.multipleErrors(
                StatusResponse.ERROR, errorResponseList
        ));
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<APIResponse<String>> entityNotFound(EntityNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(APIResponse.singleError(
                StatusResponse.ERROR, exception.getMessage()
        ));
    }

    @ExceptionHandler(ELearningBusnessException.class)
    public ResponseEntity<APIResponse<String>> businessException(ELearningBusnessException exception) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(APIResponse.singleError(
                StatusResponse.ERROR, exception.getMessage()
        ));
    }
}
