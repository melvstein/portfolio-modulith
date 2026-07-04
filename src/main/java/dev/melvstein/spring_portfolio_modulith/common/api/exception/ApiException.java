package dev.melvstein.spring_portfolio_modulith.common.api.exception;

import dev.melvstein.spring_portfolio_modulith.common.api.enm.ApiResponseEnum;
import org.springframework.http.HttpStatus;

public class ApiException extends RuntimeException {

    int code;
    HttpStatus httpStatus;

    public ApiException(String message) {
        super(message);
        this.code = HttpStatus.INTERNAL_SERVER_ERROR.value();
        this.httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
    }

    public ApiException(String message, Throwable cause) {
        super(message, cause);
        this.code = HttpStatus.INTERNAL_SERVER_ERROR.value();
        this.httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
    }

    public ApiException(ApiResponseEnum response) {
        super(response.getMessage());
        this.code = response.getCode();
        this.httpStatus = response.getHttpStatus();
    }

    public ApiException(int code, String message, HttpStatus httpStatus) {
        super(message);
        this.code = code;
        this.httpStatus = httpStatus;
    }

    public ApiException(int code, String message) {
        super(message);
        this.code = code;
        this.httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
    }

    public ApiException(ApiResponseEnum response, String message) {
        super(message);
        this.code = response.getCode();
        this.httpStatus = response.getHttpStatus();
    }
}
