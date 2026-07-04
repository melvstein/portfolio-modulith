package dev.melvstein.spring_portfolio_modulith.auth.internal.controller;

import dev.melvstein.spring_portfolio_modulith.auth.api.dto.AuthRegisterRequestDto;
import dev.melvstein.spring_portfolio_modulith.auth.api.enm.AuthApiResponseEnum;
import dev.melvstein.spring_portfolio_modulith.auth.api.entity.User;
import dev.melvstein.spring_portfolio_modulith.auth.internal.service.UserService;
import dev.melvstein.spring_portfolio_modulith.auth.api.mapper.UserMapper;
import dev.melvstein.spring_portfolio_modulith.auth.api.vo.AuthRegisterResponseVo;
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
    public ResponseEntity<AuthRegisterResponseVo> registerUser(
            @Valid @RequestBody AuthRegisterRequestDto request
    ) {
        User savedUser = userService.save(UserMapper.toEntity(request));

        return ResponseEntity.ok(
                AuthRegisterResponseVo.builder()
                        .code(AuthApiResponseEnum.SUCCESS.getCode())
                        .message(AuthApiResponseEnum.SUCCESS.getMessage())
                        .data(UserMapper.toDto(savedUser))
                        .build()
        );
    }
}
