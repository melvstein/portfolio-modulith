package dev.melvstein.spring_portfolio_modulith.common.enm;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ApiResponse {

    SUCCESS(0, "Success", HttpStatus.OK),
    FAILED(1, "Failed", HttpStatus.BAD_REQUEST),
    INTERNAL_SERVER_ERROR(2, "Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR),
    INVALID_CREDENTIALS(3, "Invalid Credentials", HttpStatus.UNAUTHORIZED),
    USER_NOT_FOUND(4, "User Not Found", HttpStatus.NOT_FOUND),;

    private final int code;
    private final String message;
    private final HttpStatus httpStatus;
}
