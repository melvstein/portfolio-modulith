package dev.melvstein.spring_portfolio_modulith.auth.api.facade;

import dev.melvstein.spring_portfolio_modulith.auth.api.dto.UserDto;

import java.util.List;
import java.util.UUID;

public interface AuthFacade {

    List<UserDto> getAllUsers();
    List<UserDto> getAllUsersByIds(List<UUID> userIds);
}
