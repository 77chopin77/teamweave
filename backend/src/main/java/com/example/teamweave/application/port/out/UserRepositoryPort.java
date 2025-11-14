package com.example.teamweave.application.port.out;

import com.example.teamweave.domain.model.User;
import java.util.Optional;


/* Userエンティティを永続化層とやり取りするための出力ポート */ 
public interface UserRepositoryPort {

    // メールアドレスからユーザーを検索する
    Optional<User> findByEmail(String email);

    // ユーザー情報を保存する
    void save(User user);
}
