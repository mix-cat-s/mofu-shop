package com.mofushop.mofushop.security.domain;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserQuery {
  /**
   * 全てのユーザーを取得します。
   */
  List<User> getAll();

  /**
   * ユーザーを取得します。
   */
  Optional<User> getById(UUID userId);
}
