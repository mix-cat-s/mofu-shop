package com.mofushop.security;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.mofushop.security.domain.User;
import com.mofushop.security.domain.UserCommand;
import com.mofushop.security.domain.UserQuery;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

// TODO doma使ってみるサンプルとして作っただけ

@Component
@RequiredArgsConstructor
@Slf4j
public class Runner implements ApplicationRunner {
  private final UserQuery userQuery;
  private final UserCommand userCommand;

  @Override
  public void run(ApplicationArguments args) throws Exception {
    final var mofu = User.createNewUser("mofu");
    log.info("mofu is %s".formatted(mofu));
    userCommand.save(mofu);
    this.userQuery
        .getAll()
        .stream()
        .map(User::toString)
        .forEach(log::info);
  }

}
