package com.mofushop.config.spring;

import java.util.UUID;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import mazewands.persistence.Identifier;

@Component
class StringToIdentifierConverter implements Converter<String, Identifier<?>> {
  @Override
  @Nullable
  public Identifier<?> convert(@Nullable String source) {
    if (source == null) {
      return null;
    }
    return Identifier.of(UUID.fromString(source));
  }
}
