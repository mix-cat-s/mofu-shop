package com.mofushop.mofushop.security.domain;

import java.util.List;

public interface UserCommand {
  /**
   * ユーザーを保存します。
   * 
   * @param user ユーザー
   * @return 保存したユーザー
   */
  default User save(User user) {
    return saveAll(List.of(user)).stream().findAny().orElseThrow();
  }

  /**
   * 複数のユーザーをすべて保存します。
   * 
   * @param users 複数のユーザー
   * @return 保存した複数のユーザー
   */
  List<User> saveAll(List<User> users);
}
