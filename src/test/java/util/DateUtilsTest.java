package util;

import org.junit.Test;

import java.text.ParseException;

public class DateUtilsTest {

  @Test
  public void localDateTest() {
    System.out.println(DateUtils.getLocalDate());
  }

  @Test
  public void dateTest() throws ParseException {
    System.out.println(DateUtils.getDate());
  }
}
