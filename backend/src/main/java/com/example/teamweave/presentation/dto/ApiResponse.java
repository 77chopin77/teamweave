package com.example.teamweave.presentation.dto;

/* ===== APIのレスポンス（返り値）の形を決めるための共通フォーマットクラス ===== */ 

// <T>というは型しか持ってない。GlobalExceptionHandlerで具体的な型を決める。
public record ApiResponse<T>(
    boolean success,
    String message,
    T data
) {
    public static <T> ApiResponse<T> ok(T data) {
        return new ApiResponse<>(true, null, data);
    }

    public static ApiResponse<Void> message(String message) {
        return new ApiResponse<>(true, message, null);
    }

    public static ApiResponse<Void> error(String message) {
        return new ApiResponse<>(false, message, null);
    }
}
