package com.mofushop.config.doma.listener;

import java.time.LocalDateTime;

import org.seasar.doma.jdbc.entity.EntityListener;
import org.seasar.doma.jdbc.entity.PreInsertContext;
import org.seasar.doma.jdbc.entity.PreUpdateContext;
import org.springframework.stereotype.Component;

import mazewands.persistence.Timestamped;

/**
 * タイムスタンプが付いたエンティティのエンティティリスナーです。
 * insert、updateの際にタイムスタンプを差し込みます。
 */
@Component
public class TimestampedEntityListener<T extends Timestamped<T>> implements EntityListener<T> {
  @Override
  public void preInsert(T entity, PreInsertContext<T> context) {
    final var currentDateTime = LocalDateTime.now();
    context.setNewEntity(
        entity
            .withCreatedAt(currentDateTime)
            .withUpdatedAt(currentDateTime));
  }

  @Override
  public void preUpdate(T entity, PreUpdateContext<T> context) {
    final var currentDateTime = LocalDateTime.now();
    context.setNewEntity(
        entity
            .withUpdatedAt(currentDateTime));
  }
}
