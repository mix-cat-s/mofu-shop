package com.mofushop.mofushop.security.repository;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import org.seasar.doma.Entity;
import org.seasar.doma.Id;
import org.seasar.doma.Metamodel;
import org.seasar.doma.Table;

import com.mofushop.mofushop.security.domain.User;

@Entity(immutable = true, metamodel = @Metamodel)
@Table(name = "users")
record UserEntity(
    @Id UUID id,
    String userName,
    boolean active,
    Optional<LocalDateTime> createdAt,
    Optional<LocalDateTime> updatedAt) {

  User toDomain() {
    return new User(
        this.id(),
        this.userName(),
        this.active());
  }

  static UserEntity from(User user) {
    return new UserEntity(
        user.id(),
        user.userName(),
        user.active(),
        Optional.of(LocalDateTime.now()), // TODO ここじゃなくinsert時のフックで入れる
        Optional.empty());
  }

}
