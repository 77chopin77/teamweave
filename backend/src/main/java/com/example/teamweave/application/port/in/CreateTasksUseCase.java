package com.example.teamweave.application.port.in;

import com.example.teamweave.application.usecase.dto.TaskDto;


/* タスク作成というユースケースのインターフェースを知恵ぎしているところ */
public interface CreateTasksUseCase {

    // タスク作成を実行するメソッド
    TaskDto handle(CreateTaskCommand cmd);

    // タスク作成に必要な入力パラメータをまとめた「コマンドオブジェクト」（何をしたいのか、それに必要なデータをまとめたオブジェクト）
    record CreateTaskCommand(String title, String description, String assigneeId, String dueDateIso) {
    }
}
