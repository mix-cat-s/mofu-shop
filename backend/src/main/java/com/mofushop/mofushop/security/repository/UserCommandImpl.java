package com.mofushop.mofushop.security.repository;

import java.util.List;

import org.seasar.doma.jdbc.criteria.Entityql;
import org.seasar.doma.jdbc.criteria.NativeSql;
import org.springframework.stereotype.Repository;

import com.mofushop.mofushop.security.domain.User;
import com.mofushop.mofushop.security.domain.UserCommand;

import lombok.RequiredArgsConstructor;
import mazewands.persistence.Identifier;

@Repository
@RequiredArgsConstructor
class UserCommandImpl implements UserCommand {
  private final Entityql entityql;
  private final NativeSql nativeSql;

  private static final UserEntity_ user_ = new UserEntity_();

  @Override
  public List<User> saveAll(List<User> users) {
    final var entities = users.stream().map(UserEntity::from).toList();
    final var result = this.entityql.insert(user_, entities).execute();
    return result.getEntities().stream().map(UserEntity::toDomain).toList();
  }

  @Override
  public List<User> updateAll(List<User> users) {
    final var entities = users.stream().map(UserEntity::from).toList();
    final var result = this.entityql.update(user_, entities).execute();
    return result.getEntities().stream().map(UserEntity::toDomain).toList();
  }

  @Override
  public long deleteByIds(List<Identifier<User>> ids) {
    if (ids.isEmpty()) {
      return 0;
    }
    return this.nativeSql
        .delete(user_)
        .where(c -> c.in(user_.id, ids))
        .execute();
  }

  @Override
  public long deleteAll() {
    return this.nativeSql
        .delete(user_, settings -> settings.setAllowEmptyWhere(true))
        .execute();
  }
}
