package com.backendjava.app.models.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class ExceptionResponseDto {

    private LocalDate date;
    private String message;
    private List<String> details;

    public ExceptionResponseDto(LocalDate date, String message) {
        this.date = date;
        this.message = message;
    }

    public ExceptionResponseDto(LocalDate date, String message, List<String> details) {
        this.date = date;
        this.message = message;
        this.details = details;
    }
}
