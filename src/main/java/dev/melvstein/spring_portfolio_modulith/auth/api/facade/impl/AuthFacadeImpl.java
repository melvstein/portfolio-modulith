package dev.melvstein.spring_portfolio_modulith.auth.api.facade.impl;

import dev.melvstein.spring_portfolio_modulith.auth.api.dto.UserDto;
import dev.melvstein.spring_portfolio_modulith.auth.api.entity.User;
import dev.melvstein.spring_portfolio_modulith.auth.api.facade.AuthFacade;
import dev.melvstein.spring_portfolio_modulith.auth.api.mapper.UserMapper;
import dev.melvstein.spring_portfolio_modulith.auth.internal.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Slf4j
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

    @Override
    public List<UserDto> getAllUsersByIds(List<UUID> userIds) {
        List<User> users = userService.getAllUsersByIds(userIds);

        log.info("melvstein Found {} users for ids: {}", users.size(), userIds);
        log.info("melvstein Found {} users", users);

        return users.stream()
                .map(UserMapper::toDto)
                .toList();
    }
}
