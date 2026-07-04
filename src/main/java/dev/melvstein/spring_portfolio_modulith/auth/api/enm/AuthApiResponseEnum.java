package dev.melvstein.spring_portfolio_modulith.auth.api.enm;

import dev.melvstein.spring_portfolio_modulith.common.api.enm.ApiResponseEnum;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum AuthApiResponseEnum implements ApiResponseEnum {

    SUCCESS(0, "Success", HttpStatus.OK),
    FAILED(1, "Failed", HttpStatus.BAD_REQUEST),
    INTERNAL_SERVER_ERROR(2, "Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR),
    INVALID_CREDENTIALS(3, "Invalid Credentials", HttpStatus.UNAUTHORIZED),
    USER_NOT_FOUND(4, "User Not Found", HttpStatus.NOT_FOUND),
    USERNAME_ALREADY_EXISTS(5, "Username Already Exists", HttpStatus.CONFLICT),
    EMAIL_ALREADY_EXISTS(6, "Email Already Exists", HttpStatus.BAD_REQUEST),
    CONTACT_NUMBER_ALREADY_EXISTS(7, "Contact Number Already Exists", HttpStatus.BAD_REQUEST);

    private final int code;
    private final String message;
    private final HttpStatus httpStatus;
}
