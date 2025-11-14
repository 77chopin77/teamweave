package com.example.teamweave.domain.model;

import java.util.Objects;

/* ユーザーを表すドメインモデル */
public class User {
    private final UserId id;    // ユーザーID
    private final String email; // メールアドレス
    private String passwordHash;// ハッシュ化されたパスワード

    // コンストラクタ（全フィールド初期化し、nullを禁止）
    public User(UserId id, String email, String passwordHash) {
        this.id = Objects.requireNonNull(id);
        this.email = Objects.requireNonNull(email);
        this.passwordHash = Objects.requireNonNull(passwordHash);
    }

    // ゲッター
    public UserId getId() { return id; }
    public String getEmail() { return email; }
    public String getPasswordHash() { return passwordHash; }

    // パスワード変更
    public void changePassword(String newHash) {
        this.passwordHash = Objects.requireNonNull(newHash);
    }
}
