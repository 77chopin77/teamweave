package com.example.teamweave.infrastructure.persistence;

import com.example.teamweave.infrastructure.persistence.entity.TaskJpaEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository

/* tasksテーブルにアクセスするためのリポジトリ。SQLを書かずにデータを取得・保存できる「自動SQL生成リポジトリ */
public interface TaskJpaRepository extends CrudRepository<TaskJpaEntity, UUID> {

    // タスクの所有者（user_id）で検索
    List<TaskJpaEntity> findByUserId(UUID userId);

    // 担当者（assignee_id）で検索
    List<TaskJpaEntity> findByAssigneeId(UUID assigneeId);
}
