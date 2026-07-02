package dev.melvstein.spring_portfolio_modulith.auth.internal.controller;

import dev.melvstein.spring_portfolio_modulith.auth.dto.AuthRegisterRequestDto;
import dev.melvstein.spring_portfolio_modulith.auth.entity.User;
import dev.melvstein.spring_portfolio_modulith.auth.internal.service.UserService;
import dev.melvstein.spring_portfolio_modulith.auth.mapper.UserMapper;
import dev.melvstein.spring_portfolio_modulith.auth.vo.AuthRegisterResponseVo;
import dev.melvstein.spring_portfolio_modulith.common.enm.ApiResponse;
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
                        .code(ApiResponse.SUCCESS.getCode())
                        .message(ApiResponse.SUCCESS.getMessage())
                        .data(UserMapper.toDto(savedUser))
                        .build()
        );
    }
}
