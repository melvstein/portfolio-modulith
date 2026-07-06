package dev.melvstein.spring_portfolio_modulith.auth.api.facade.impl;

import dev.melvstein.spring_portfolio_modulith.auth.api.dto.UserDto;
import dev.melvstein.spring_portfolio_modulith.auth.api.entity.User;
import dev.melvstein.spring_portfolio_modulith.auth.api.facade.AuthFacade;
import dev.melvstein.spring_portfolio_modulith.auth.api.mapper.UserMapper;
import dev.melvstein.spring_portfolio_modulith.auth.internal.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthFacadeImpl implements AuthFacade {

    private final UserService userService;

    public AuthFacadeImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userService.getAllUsers();

        return users.stream()
                .map(UserMapper::toDto)
                .toList();
    }
}
