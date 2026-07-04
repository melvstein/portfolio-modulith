package dev.melvstein.spring_portfolio_modulith.common.api.enm;

import org.springframework.http.HttpStatus;

public interface ApiResponseEnum {
    int getCode();
    String getMessage();
    HttpStatus getHttpStatus();
}
