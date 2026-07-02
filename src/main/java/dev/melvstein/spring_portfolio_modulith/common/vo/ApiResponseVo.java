package dev.melvstein.spring_portfolio_modulith.common.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import dev.melvstein.spring_portfolio_modulith.common.enm.ApiResponse;
import lombok.Builder;

@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public record ApiResponseVo (

        int code,
        String message,
        Object data
) implements BaseResponseVo{

    public ApiResponseVo error(ApiResponse response) {
        return ApiResponseVo.builder()
                .code(response.getCode())
                .message(response.getMessage())
                .build();
    }
}
