package util;

import org.junit.Test;

import java.nio.file.Path;
import java.nio.file.Paths;

public class StringUtilsTest {
  @Test
  public void jsonParse() {
    String[] column = {
        "EMPLOYEE_ID",
        "FIRST_NAME",
        "LAST_NAME",
        "EMAIL",
        "PHONE_NUMBER",
        "HIRE_DATE",
        "JOB_ID",
        "SALARY",
        "COMMISSION_PCT",
        "MANAGER_ID",
        "DEPARTMENT_ID"
    };


    Path path = Paths.get("/Users/myungsubso/Desktop/SELECT_t___FROM_HR_EMPLOYEES_t.tsv");
    StringUtils.jsonParse(path, column, "\t");

  }

  @Test
  public void dateTest() {

  }
}
