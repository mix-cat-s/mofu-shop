package com.mofushop.security.usecase;

import org.springframework.stereotype.Service;

import com.mofushop.exception.DuplicatedUserNameException;
import com.mofushop.security.domain.User;
import com.mofushop.security.domain.UserCommand;
import com.mofushop.security.domain.UserQuery;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * ユーザーを作成するサービス
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class UserCreator {
  private final UserQuery userQuery;
  private final UserCommand userCommand;

  /**
   * 新しいユーザーを作成します。
   * 
   * @param form ユーザー作成フォーム
   * @return 作成したユーザー
   */
  public User createNewUser(Form form) {
    if (userQuery.existsUserName(form.userName())) {
      throw new DuplicatedUserNameException(form.userName);
    }
    final var createdUser = this.userCommand.save(User.createNewUser(form.userName()));
    log.info("ユーザーを作成しました。[{}]", createdUser);
    return createdUser;
  }

  /**
   * ユーザー作成フォーム
   */
  public record Form(
      /** ユーザー名 */
      String userName) {
  }
}
