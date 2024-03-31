package com.mofushop.security.domain;

import mazewands.persistence.Identifiable;
import mazewands.persistence.Identifier;

public record User(
    Identifier<User> id,
    String userName,
    boolean active) implements Identifiable<User> {
  public static User createNewUser(String userName) {
    return new User(Identifier.randomUUID(), "mofu", true);
  }
}
