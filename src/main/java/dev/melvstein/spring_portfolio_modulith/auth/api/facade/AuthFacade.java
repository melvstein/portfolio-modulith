package dev.melvstein.spring_portfolio_modulith.auth.api.facade;

import dev.melvstein.spring_portfolio_modulith.auth.api.dto.UserDto;

import java.util.List;

public interface AuthFacade {

    List<UserDto> getAllUsers();
}
