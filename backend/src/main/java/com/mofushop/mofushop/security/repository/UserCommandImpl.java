package com.mofushop.mofushop.security.repository;

import java.util.List;

import org.seasar.doma.jdbc.criteria.Entityql;
import org.springframework.stereotype.Repository;

import com.mofushop.mofushop.security.domain.User;
import com.mofushop.mofushop.security.domain.UserCommand;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
class UserCommandImpl implements UserCommand {
  private final Entityql entityql;
  private static final UserEntity_ user_ = new UserEntity_();

  @Override
  public List<User> saveAll(List<User> users) {
    final var entities = users.stream().map(UserEntity::from).toList();
    final var result = this.entityql.insert(user_, entities).execute();
    return result.getEntities().stream().map(UserEntity::toDomain).toList();
  }

}
