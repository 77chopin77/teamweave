package com.example.teamweave.application.port.in;

import com.example.teamweave.application.usecase.dto.TaskDto;
import java.util.List;

/* 「タスク一覧を取得する」ユースケースの契約（入力ポート） */ 
public interface GetTasksUseCase {

    // タスク一覧を取得するユースケースメソッド
    List<TaskDto> handle();
}
