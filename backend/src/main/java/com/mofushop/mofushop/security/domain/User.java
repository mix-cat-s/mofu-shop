package com.mofushop.mofushop.security.domain;

import mazeneko.mazewands.persistence.Identifiable;
import mazeneko.mazewands.persistence.Identifier;

public record User(
    Identifier<User> id,
    String userName,
    boolean active) implements Identifiable<User> {
  public static User createNewUser(String userName) {
    return new User(Identifier.randomUUID(), "mofu", true);
  }
}
