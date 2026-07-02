package dev.melvstein.spring_portfolio_modulith.auth.internal.repository;

import dev.melvstein.spring_portfolio_modulith.auth.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    Optional<User> findByUsername(String username);
}
