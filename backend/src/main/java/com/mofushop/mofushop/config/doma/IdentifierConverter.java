package com.mofushop.mofushop.config.doma;

import java.util.UUID;

import org.seasar.doma.ExternalDomain;
import org.seasar.doma.jdbc.domain.DomainConverter;

import mazewands.persistence.Identifier;

@ExternalDomain
public class IdentifierConverter implements DomainConverter<Identifier<?>, Object> {

  @Override
  public Object fromDomainToValue(Identifier<?> domain) {
    return domain.value();
  }

  @Override
  public Identifier<?> fromValueToDomain(Object value) {
    if (value == null) {
      return null;
    }
    return Identifier.of((UUID) value);
  }
}
