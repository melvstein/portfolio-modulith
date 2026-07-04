package dev.melvstein.spring_portfolio_modulith.auth.api.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import dev.melvstein.spring_portfolio_modulith.auth.api.dto.UserDto;
import dev.melvstein.spring_portfolio_modulith.common.api.vo.BaseResponseVo;
import lombok.Builder;

@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public record AuthRegisterResponseVo(

        int code,
        String message,
        UserDto data
) implements BaseResponseVo {
}
