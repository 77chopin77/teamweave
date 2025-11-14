package com.example.teamweave.presentation.exception;

import com.example.teamweave.presentation.dto.ApiResponse;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.MethodArgumentNotValidException;
import java.util.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(SecurityException.class) // SecurityException が発生したら、このメソッドで処理させる宣言をする
    public ResponseEntity<ApiResponse<Void>> handleSecurity(SecurityException e) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN) // HTTPステータス403(アクセス禁止)を設定
                .body(ApiResponse.error(e.getMessage())); // エラーメッセージを入れたJSONをここに入れる
    }

    @ExceptionHandler(IllegalArgumentException.class) // 同様
    public ResponseEntity<ApiResponse<Void>> handleIllegal(IllegalArgumentException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST) // HTTPステータスを 400（Bad Request）に設定
                .body(ApiResponse.error(e.getMessage())); // ApiResponseの「エラー用」静的メソッドでJSONを作る。
    }

    @ExceptionHandler(MethodArgumentNotValidException.class) // 入力チェックに引っかかった時の例外
    public ResponseEntity<ApiResponse<Map<String, String>>> handleValidation(MethodArgumentNotValidException e) {
        Map<String, String> errors = new HashMap<>(); // どの項目が何でダメだったかをまとめた一覧
        e.getBindingResult().getFieldErrors().forEach(err -> errors.put(err.getField(), err.getDefaultMessage()));
        return ResponseEntity.badRequest() // HTTPステータス400を設定する
                .body(new ApiResponse<>(false, "Validation failed", errors)); // 共通のレスポンス形式に整える
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Void>> handleGeneral(Exception e) { 
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR) // ステータスコード 500（サーバーのバグや予期せぬエラー）
                .body(ApiResponse.error("Unexpected error: " + e.getClass().getSimpleName())); // エラーの型名をメッセージとしてJSONに詰めて返す
    }
}
