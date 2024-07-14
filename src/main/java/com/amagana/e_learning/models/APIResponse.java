package com.amagana.e_learning.models;

import com.amagana.e_learning.dto.ErrorsDto;
import com.amagana.e_learning.enums.StatusResponse;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.data.domain.Page;

import java.util.List;


@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class APIResponse<T> {
    private String message;
    private T results;
    private StatusResponse statusResponse;
    private List<ErrorsDto> errors;

    public static <T> APIResponse<T> singeResult(StatusResponse statusResponse, T results){
        return APIResponse.<T>builder()
               .statusResponse(statusResponse)
               .results(results)
               .build();
    }
    public static <T> APIResponse<List<T>> multipleResults(StatusResponse statusResponse, List<T> results){
        return APIResponse.<List<T>>builder()
               .statusResponse(statusResponse)
               .results(results)
               .build();
    }
    public static <T> APIResponse<Page<T>> pageResults(StatusResponse statusResponse, Page<T> results){
        return APIResponse.<Page<T>>builder()
                .statusResponse(statusResponse)
                .results(results)
                .build();
    }
    public static <T> APIResponse<T> singleError(StatusResponse statusResponse, String message) {
        return APIResponse.<T>builder()
               .statusResponse(statusResponse)
               .message(message)
               .build();
    }
    public static <T> APIResponse<T> multipleErrors(StatusResponse statusResponse, List<ErrorsDto> errors){
        return APIResponse.<T>builder()
               .statusResponse(statusResponse)
               .errors(errors)
               .build();
    }
}
