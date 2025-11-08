package com.example.teamweave.infrastructure.persistence;

import com.example.teamweave.infrastructure.persistence.entity.UserJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface UserJpaRepository extends JpaRepository<UserJpaEntity, UUID> {
    java.util.Optional<UserJpaEntity> findByEmail(String email);
}
