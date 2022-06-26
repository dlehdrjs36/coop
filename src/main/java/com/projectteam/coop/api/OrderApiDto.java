package com.projectteam.coop.api;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Getter
public class OrderApiDto {
    private final String message;
    private final HttpStatus status;
}
