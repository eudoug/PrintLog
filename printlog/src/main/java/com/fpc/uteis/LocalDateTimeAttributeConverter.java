package com.fpc.uteis;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;


@Converter(autoApply=true)
public class LocalDateTimeAttributeConverter
  implements AttributeConverter<LocalDateTime, Timestamp>
{
  public LocalDateTimeAttributeConverter() {}
  
  public Timestamp convertToDatabaseColumn(LocalDateTime localDateTime)
  {
    if (localDateTime != null) {
      return Timestamp.valueOf(localDateTime);
    }
    return null;
  }
  



  public LocalDateTime convertToEntityAttribute(Timestamp timestamp)
  {
    if (timestamp != null) {
      return timestamp.toLocalDateTime();
    }
    return null;
  }
}