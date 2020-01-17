package eos;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
  public static void main(String[] args) {
    Path path = Paths.get("/Users/myungsubso/Desktop/참고자료) Document/계열사 Windows EOS 대상리스트(콜시스템포함)_20190618 v0.1.xlsx");
    eosServer(path);
  }

  private static void eosServer(Path path) {
    try (BufferedReader br = Files.newBufferedReader(path)) {
      XSSFWorkbook book = new XSSFWorkbook(path.toFile());

      for (int i = 0; i < book.getNumberOfSheets(); i++) {
        XSSFSheet sheet = book.getSheetAt(i);
        for (int j = 0; j < sheet.getPhysicalNumberOfRows(); j++) {
          XSSFRow row = sheet.getRow(j);
          for (int k = 0; k < row.getPhysicalNumberOfCells(); k++) {
            row.getCell(k);

          }
        }
      }


    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private static void eosDTNT() {

    return;
  }
}
 