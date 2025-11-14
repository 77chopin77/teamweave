package com.example.teamweave.application.port.in;


/* アプリケーション層の入り口 (Controllerの依存先)*/
public interface AuthUseCase {
    void signup(String email, String password);
    String login(String email, String password);
}
