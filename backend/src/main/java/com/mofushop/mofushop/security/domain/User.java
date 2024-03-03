package com.mofushop.mofushop.security.domain;

import java.util.UUID;

public record User(UUID id, String userName, boolean active) {
  public static User createNewUser(String userName) {
    return new User(UUID.randomUUID(), "mofu", true);
  }
}
