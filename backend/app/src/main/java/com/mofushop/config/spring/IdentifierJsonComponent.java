package com.mofushop.config.spring;

import java.io.IOException;
import java.util.UUID;

import org.springframework.boot.jackson.JsonComponent;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import mazewands.persistence.Identifier;

@JsonComponent(type = Identifier.class)
class IdentifierJsonComponent {
  public static class SerializerImpl extends StdSerializer<Identifier<?>> {
    protected SerializerImpl() {
      super(Identifier.class, false);
    }

    @Override
    public void serialize(Identifier<?> value, JsonGenerator gen, SerializerProvider provider)
        throws IOException {
      gen.writeString(value.value().toString());
    }
  }

  public static class DeserializerImpl extends StdDeserializer<Identifier<?>> {
    protected DeserializerImpl() {
      super(Identifier.class);
    }

    @Override
    public Identifier<?> deserialize(JsonParser parser, DeserializationContext context)
        throws IOException, JsonProcessingException {
      return Identifier.of(UUID.fromString(parser.getValueAsString()));
    }
  }
}
