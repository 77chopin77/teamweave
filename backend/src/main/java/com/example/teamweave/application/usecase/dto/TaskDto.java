package com.example.teamweave.application.usecase.dto;

import com.example.teamweave.domain.model.Task;
import java.util.UUID;


/* ドメインモデル（Task）を外部に渡すためのデータ転送オブジェクト（DTO） */
public record TaskDto(
        UUID id,            // タスクID
        String title,       // タイトル
        String description, // 説明
        UUID assigneeId,    // 担当者のユーザーID
        String status,      // タスクの状態（Enum - String)
        String dueDateIso   // 期限（日付）をISO形式文字列で表現
) {
    //  ドメインモデル（Task）をDTOに変換する静的メソッド
    public static TaskDto from(Task t) {
        return new TaskDto(
            // TaskIdなどValueObjectの中から値を取り出す
            t.getId().value(),
            t.getTitle(),
            t.getDescription(),

            // 担当者がいない場合は null にして安全に返す
            t.getAssignee() == null ? null : t.getAssignee().value(),

            // Enum型のstatusを文字列に変換
            t.getStatus().name(),

            // 期限日をISO文字列に変換
            t.getDueDate() == null ? null : t.getDueDate().toString()
        );
    }
}
