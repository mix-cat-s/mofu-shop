package com.mofushop.mofushop.config.doma;

import java.util.UUID;

import org.seasar.doma.ExternalDomain;
import org.seasar.doma.jdbc.domain.DomainConverter;

@ExternalDomain
public class UUIDConverter implements DomainConverter<UUID, Object> {

  @Override
  public Object fromDomainToValue(UUID domain) {
    return domain;
  }

  @Override
  public UUID fromValueToDomain(Object value) {
    if (value == null) {
      return null;
    }
    return (UUID) value;
  }
}
