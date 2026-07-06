package dev.melvstein.spring_portfolio_modulith.auth.internal.controller;

import dev.melvstein.spring_portfolio_modulith.auth.api.dto.AuthRegisterRequestDto;
import dev.melvstein.spring_portfolio_modulith.auth.api.dto.UserDto;
import dev.melvstein.spring_portfolio_modulith.auth.api.enm.AuthApiResponseEnum;
import dev.melvstein.spring_portfolio_modulith.auth.api.entity.User;
import dev.melvstein.spring_portfolio_modulith.auth.internal.service.UserService;
import dev.melvstein.spring_portfolio_modulith.auth.api.mapper.UserMapper;
import dev.melvstein.spring_portfolio_modulith.common.api.vo.ApiResponseVo;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponseVo<UserDto>> registerUser(
            @Valid @RequestBody AuthRegisterRequestDto request
    ) {
        User savedUser = userService.save(UserMapper.toEntity(request));
        UserDto userDto = UserMapper.toDto(savedUser);

        return ResponseEntity.ok(
                ApiResponseVo.<UserDto>builder()
                        .code(AuthApiResponseEnum.SUCCESS.getCode())
                        .message(AuthApiResponseEnum.SUCCESS.getMessage())
                        .data(userDto)
                        .build()
        );
    }
}
