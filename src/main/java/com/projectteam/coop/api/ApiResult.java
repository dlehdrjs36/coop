package com.projectteam.coop.api;

import com.projectteam.coop.api.exhandler.ErrorResult;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class ApiResult<T> {
    private final boolean success;
    private final T response;
    private final ErrorResult apiError;
}
