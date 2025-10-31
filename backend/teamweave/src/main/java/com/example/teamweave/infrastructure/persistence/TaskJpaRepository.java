package com.example.teamweave.infrastructure.persistence;

import com.example.teamweave.infrastructure.persistence.entity.TaskJpaEntity;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface TaskJpaRepository extends CrudRepository<TaskJpaEntity, Long> {

    List<TaskJpaEntity> findByUserId(Long userId);

    List<TaskJpaEntity> findByAssigneeId(Long assigneeId); // ✅ 追加
}
