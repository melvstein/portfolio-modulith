package dev.melvstein.spring_portfolio_modulith.common.exception;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import dev.melvstein.spring_portfolio_modulith.common.enm.ApiResponse;
import dev.melvstein.spring_portfolio_modulith.common.vo.ApiResponseVo;
import dev.melvstein.spring_portfolio_modulith.common.vo.BaseResponseVo;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<BaseResponseVo> handleHttpMessageNotReadable(HttpMessageNotReadableException e) {
        if (e.getCause() instanceof InvalidFormatException ife
                && ife.getTargetType().isEnum()) {

            String fieldName = ife.getPath().stream()
                    .findFirst()
                    .map(JsonMappingException.Reference::getFieldName)
                    .orElse("field");

            String allowedValues = Arrays.stream(
                            ife.getTargetType().getEnumConstants()
                    )
                    .map(Object::toString)
                    .collect(Collectors.joining(", "));

            return ResponseEntity.badRequest()
                    .body(ApiResponseVo.builder()
                            .code(ApiResponse.FAILED.getCode())
                            .message(String.format(
                                    "%s must be one of: %s",
                                    fieldName,
                                    allowedValues
                            ))
                            .build());
        }

        return ResponseEntity.badRequest()
                .body(ApiResponseVo.builder()
                        .code(ApiResponse.FAILED.getCode())
                        .message("Invalid request payload")
                        .build());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponseVo> handleValidationException(
            MethodArgumentNotValidException ex) {

        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult()
                .getFieldErrors()
                .forEach(error ->
                        errors.put(error.getField(), error.getDefaultMessage()));

        ApiResponseVo response = ApiResponseVo.builder()
                .code(ApiResponse.FAILED.getCode())
                .message("Validation failed")
                .data(errors)
                .build();

        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ApiResponseVo> handleApiException(ApiException ex) {
        return ResponseEntity.status(ex.httpStatus)
                .body(ApiResponseVo.builder()
                        .code(ex.code)
                        .message(ex.getMessage())
                        .build());
    }
}
