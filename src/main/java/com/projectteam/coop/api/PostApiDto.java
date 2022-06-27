package com.projectteam.coop.api;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Getter
@Setter
public class PostApiDto {
    private final String message;
    private final HttpStatus status;
    private final boolean recommendAt;
    private final Long recommendCount;
}
