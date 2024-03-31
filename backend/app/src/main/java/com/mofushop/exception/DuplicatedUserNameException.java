package com.mofushop.exception;

import java.util.List;

/**
 * ユーザー名が重複したときの例外
 */
public final class DuplicatedUserNameException extends CustomException {
  private static final String description = "すでに使用されているユーザー名です。";
  /** 重複したユーザー名 */
  private final List<String> duplicatedUsernames;

  public DuplicatedUserNameException(String duplicatedUsername) {
    super("%s : %s".formatted(description, duplicatedUsername));
    this.duplicatedUsernames = List.of(duplicatedUsername);
  }

  public DuplicatedUserNameException(List<String> duplicatedUsernames) {
    super("%s : %s".formatted(description, duplicatedUsernames));
    this.duplicatedUsernames = duplicatedUsernames;
  }

  @Override
  public String getDescription() {
    return description;
  }

  @Override
  public List<String> getContents() {
    return duplicatedUsernames;
  }
}
