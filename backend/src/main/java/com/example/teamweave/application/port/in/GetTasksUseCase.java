package com.example.teamweave.application.port.in;

import com.example.teamweave.application.usecase.dto.TaskDto;
import java.util.List;

public interface GetTasksUseCase {
    List<TaskDto> handle();
}
