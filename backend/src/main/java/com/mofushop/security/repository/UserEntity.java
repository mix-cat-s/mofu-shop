package com.mofushop.security.repository;

import java.time.LocalDateTime;
import java.util.Optional;

import org.seasar.doma.Entity;
import org.seasar.doma.Id;
import org.seasar.doma.Metamodel;
import org.seasar.doma.Table;

import com.mofushop.config.doma.listener.TimestampedEntityListener;
import com.mofushop.security.domain.User;

import lombok.With;
import mazewands.persistence.Identifier;
import mazewands.persistence.Timestamped;

@Entity(immutable = true, metamodel = @Metamodel, listener = TimestampedEntityListener.class)
@Table(name = "users")
record UserEntity(
    @Id Identifier<User> id,
    String userName,
    boolean active,
    @With Optional<LocalDateTime> createdAt,
    @With Optional<LocalDateTime> updatedAt) implements Timestamped<UserEntity> {

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
        Optional.empty(),
        Optional.empty());
  }

}
