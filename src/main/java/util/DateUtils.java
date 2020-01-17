package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

public class DateUtils {

  public static LocalDateTime getLocalDate() {
    return LocalDateTime.parse("2016-09-21", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
//    return LocalDateTime.parse("2016-09-21 13:43:27", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
  }

  public static Date getDate() throws ParseException {
    Date now = new Date();
    System.out.println("now = " + now);
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    System.out.println("sdf = " + sdf);
    return sdf.parse(sdf.format(now));
  }
}
