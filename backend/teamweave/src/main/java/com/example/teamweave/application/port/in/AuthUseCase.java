package com.example.teamweave.application.port.in;

public interface AuthUseCase {
    void signup(String email, String password);
    String login(String email, String password);
}
