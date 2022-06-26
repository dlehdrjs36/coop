package com.projectteam.coop.api.exhandler;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Getter
public class ErrorResult {
    private final String errorName;
    private final String message;
    private final HttpStatus status;
    private final Map<String, String> validation = new HashMap<>();

    public void addValidation(String fieldName, String errorMessage) {
        this.validation.put(fieldName, errorMessage);
    }
}
