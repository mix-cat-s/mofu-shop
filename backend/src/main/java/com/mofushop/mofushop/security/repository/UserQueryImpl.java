package com.mofushop.mofushop.security.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.seasar.doma.jdbc.criteria.Entityql;
import org.springframework.stereotype.Repository;

import com.mofushop.mofushop.security.domain.User;
import com.mofushop.mofushop.security.domain.UserQuery;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
class UserQueryImpl implements UserQuery {
  private final Entityql entityql;
  private static final UserEntity_ user_ = new UserEntity_();

  @Override
  public List<User> getAll() {
    return this.entityql
        .from(user_)
        .stream()
        .map(UserEntity::toDomain)
        .toList();

  }

  @Override
  public Optional<User> getById(UUID userId) {
    return this.entityql
        .from(user_)
        .where(c -> c.eq(user_.id, userId))
        .fetchOptional()
        .map(UserEntity::toDomain);
  }

}
