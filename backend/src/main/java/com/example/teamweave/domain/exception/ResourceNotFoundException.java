package com.example.teamweave.domain.exception;

/* リソース（例: ユーザー・タスクなど）が見つからない場合に投げる独自例外クラス */
public class ResourceNotFoundException extends RuntimeException {

   // 親クラス(RuntimeException)のコンストラクタにメッセージを渡す
    public ResourceNotFoundException(String message) {
        super(message); // 親クラスのコンストラクタ呼び出し
    }
}
