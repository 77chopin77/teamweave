package com.example.teamweave.infrastructure.persistence;

import com.example.teamweave.application.port.out.UserRepositoryPort;
import com.example.teamweave.domain.model.User;
import com.example.teamweave.domain.model.UserId;
import com.example.teamweave.infrastructure.persistence.entity.UserJpaEntity;
import org.springframework.stereotype.Component;

import java.util.Optional;

/* UserドメインとDBエンティティ(UserJpaEntity)を相互変換して永続化を仲介するアダプタ */
@Component
public class UserRepositoryAdapter implements UserRepositoryPort {

    // DB操作を行うリポジトリ
    private final UserJpaRepository jpa;

    // DI注入
    public UserRepositoryAdapter(UserJpaRepository jpa) {
        this.jpa = jpa;
    }

    // メールで検索し、DBをドメイン変換する
    @Override
    public Optional<User> findByEmail(String email) {
        return jpa.findByEmail(email)
                .map(e -> new User(new UserId(e.getId()), e.getEmail(), e.getPasswordHash()));
    }

    // ドメインからデータベース変換して保存
    @Override
    public void save(User user) {
        var e = new UserJpaEntity();
        e.setId(user.getId().value());
        e.setEmail(user.getEmail());
        e.setPasswordHash(user.getPasswordHash());
        jpa.save(e);
    }
}
