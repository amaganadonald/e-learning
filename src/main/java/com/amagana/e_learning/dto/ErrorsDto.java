package com.amagana.e_learning.dto;

import lombok.Builder;

@Builder
public record ErrorsDto(String field, String message) {
}
