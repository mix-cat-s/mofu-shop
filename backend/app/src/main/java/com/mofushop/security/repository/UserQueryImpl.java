package com.mofushop.security.repository;

import java.util.List;

import org.seasar.doma.jdbc.criteria.Entityql;
import org.seasar.doma.jdbc.criteria.NativeSql;
import org.seasar.doma.jdbc.criteria.expression.Expressions;
import org.springframework.stereotype.Repository;

import com.mofushop.security.domain.User;
import com.mofushop.security.domain.UserQuery;

import lombok.RequiredArgsConstructor;
import mazewands.persistence.Identifier;

@Repository
@RequiredArgsConstructor
class UserQueryImpl implements UserQuery {
  private final Entityql entityql;
  private final NativeSql nativeSql;
  private static final UserEntity_ user_ = new UserEntity_();

  @Override
  public long count() {
    return this.nativeSql
        .from(user_)
        .select(Expressions.count())
        .fetchOne();
  }

  @Override
  public List<Identifier<User>> filterByExists(List<Identifier<User>> ids) {
    if (ids.isEmpty()) {
      return List.of();
    }
    final var existsIds = this.nativeSql
        .from(user_)
        .where(c -> c.in(user_.id, ids))
        .select(user_.id)
        .fetch();
    return ids.stream().filter(existsIds::contains).toList();
  }

  @Override
  public List<User> getAsPossibleByIds(List<Identifier<User>> ids) {
    return this.entityql
        .from(user_)
        .where(c -> c.in(user_.id, ids))
        .stream()
        .map(UserEntity::toDomain)
        .toList();
  }

  @Override
  public List<User> getAll() {
    return this.entityql
        .from(user_)
        .stream()
        .map(UserEntity::toDomain)
        .toList();
  }

  @Override
  public boolean existsUserName(String userName) {
    return this.entityql
        .from(user_)
        .where(c -> c.eq(user_.userName, userName))
        .fetchOptional()
        .isPresent();
  }
}
