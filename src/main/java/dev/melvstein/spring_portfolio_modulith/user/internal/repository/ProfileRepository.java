package dev.melvstein.spring_portfolio_modulith.user.internal.repository;

import dev.melvstein.spring_portfolio_modulith.user.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, UUID> {
}
