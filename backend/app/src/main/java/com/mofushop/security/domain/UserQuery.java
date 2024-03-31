package com.mofushop.security.domain;

import mazewands.persistence.Query;

public interface UserQuery extends Query<User, User> {
  /**
   * 存在するユーザー名ならtrueを返します。
   * 
   * @param userName ユーザー名
   * @return 存在するユーザー名ならtrue
   */
  boolean existsUserName(String userName);
}
