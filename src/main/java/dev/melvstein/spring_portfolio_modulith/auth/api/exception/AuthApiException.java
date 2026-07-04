package dev.melvstein.spring_portfolio_modulith.auth.api.exception;

import dev.melvstein.spring_portfolio_modulith.auth.api.enm.AuthApiResponseEnum;
import dev.melvstein.spring_portfolio_modulith.common.api.exception.ApiException;

public class AuthApiException extends ApiException {

    public AuthApiException(AuthApiResponseEnum response) {
        super(response.getCode(), response.getMessage(), response.getHttpStatus());
    }

    public AuthApiException(AuthApiResponseEnum response, String message) {
        super(response.getCode(), message, response.getHttpStatus());
    }
}
