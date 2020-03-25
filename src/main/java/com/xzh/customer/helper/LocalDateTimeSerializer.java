package com.xzh.customer.helper;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;

/**
 * @Author: Yuanqin DENG
 * @Date: 2018/11/22 12:02 PM
 */
public class LocalDateTimeSerializer extends JsonSerializer<ZonedDateTime> {

  private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ISO_OFFSET_DATE_TIME;

  @Override
  public void serialize(ZonedDateTime value, JsonGenerator g, SerializerProvider provider)
      throws IOException
  {
    if (useTimestamp(provider)) {
      g.writeStartArray();
      g.writeNumber(value.getYear());
      g.writeNumber(value.getMonthValue());
      g.writeNumber(value.getDayOfMonth());
      g.writeNumber(value.getHour());
      g.writeNumber(value.getMinute());
      if (value.getSecond() > 0 || value.getNano() > 0) {
        g.writeNumber(value.getSecond());
        if(value.getNano() > 0) {
          if (provider.isEnabled(SerializationFeature.WRITE_DATE_TIMESTAMPS_AS_NANOSECONDS))
            g.writeNumber(value.getNano());
          else
            g.writeNumber(value.get(ChronoField.MILLI_OF_SECOND));
        }
      }
      g.writeEndArray();
    } else {
      DateTimeFormatter dtf = FORMATTER;
      if (dtf == null) {
        dtf = _defaultFormatter();
      }
      g.writeString(value.format(dtf));
    }
  }

  protected DateTimeFormatter _defaultFormatter() {
    return DateTimeFormatter.ISO_OFFSET_DATE_TIME;
  }

  protected boolean useTimestamp(SerializerProvider provider) {
    // assume that explicit formatter definition implies use of textual format
    if (FORMATTER != null) {
      return false;
    }
    return provider.isEnabled(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
  }
}
