package com.projectteam.coop.util;

import com.projectteam.coop.api.ApiResult;
import com.projectteam.coop.api.exhandler.ErrorResult;
import org.springframework.http.HttpStatus;

public class ApiUtil {
    public static <T> ApiResult<T> success(T response) {
        return new ApiResult<>(true, response, null);
    }

    public static ApiResult<?> error(String errorName, String message, HttpStatus status){
        return new ApiResult<>(false, null, new ErrorResult(errorName, message, status));
    }
}
