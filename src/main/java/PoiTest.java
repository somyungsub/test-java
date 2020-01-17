import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Iterator;

public class PoiTest {
  public static void main(String[] args) throws IOException, InvalidFormatException {
    XSSFWorkbook workbook = new XSSFWorkbook(new File(String.valueOf(Paths.get("/Users/myungsubso/Desktop/lu.xlsx"))));
    XSSFSheet sheet = workbook.getSheet("LU정리");
    System.out.println("sheet = " + sheet);
    Iterator<Row> rowIterator = sheet.rowIterator();
    rowIterator.forEachRemaining(cells -> cells.forEach(cell -> System.out.println()));
  }
}
