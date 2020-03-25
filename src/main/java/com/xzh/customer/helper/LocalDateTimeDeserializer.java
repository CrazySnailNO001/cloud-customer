package com.xzh.customer.helper;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @Author: Yuanqin DENG
 * @Date: 2018/11/22 12:03 PM
 */
public class LocalDateTimeDeserializer extends JsonDeserializer<ZonedDateTime> {

  private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ISO_OFFSET_DATE_TIME;

  @Override
  public ZonedDateTime deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
    return ZonedDateTime.parse(p.getText(), FORMATTER);
//    return LocalDateTime.parse(p.getValueAsString(), FORMATTER);
  }

}
