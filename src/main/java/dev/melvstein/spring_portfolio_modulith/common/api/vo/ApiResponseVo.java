package dev.melvstein.spring_portfolio_modulith.common.api.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import dev.melvstein.spring_portfolio_modulith.common.api.enm.impl.ApiResponseImpl;
import lombok.Builder;

@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public record ApiResponseVo (

        int code,
        String message,
        Object data
) implements BaseResponseVo {

    public static ApiResponseVo error(ApiResponseImpl response) {
        return ApiResponseVo.builder()
                .code(response.getCode())
                .message(response.getMessage())
                .build();
    }

    public static ApiResponseVo success(Object data) {
        return ApiResponseVo.builder()
                .code(ApiResponseImpl.SUCCESS.getCode())
                .message(ApiResponseImpl.SUCCESS.getMessage())
                .data(data)
                .build();
    }

    public ApiResponseVo success() {
        return ApiResponseVo.builder()
                .code(ApiResponseImpl.SUCCESS.getCode())
                .message(ApiResponseImpl.SUCCESS.getMessage())
                .build();
    }
}
