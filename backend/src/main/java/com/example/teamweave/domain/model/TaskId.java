package com.example.teamweave.domain.model;

import java.util.UUID;

/* タスクIDを表す値オブジェクト */
public class TaskId {
    // 実際のUUID値
    private final UUID value;

    // コンストラクタ
    public TaskId(UUID value) {
        this.value = value;
    }

    // 値を取り出すためのゲッター
    public UUID value() {
        return value;
    }
}
