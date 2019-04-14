package excel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class MarkdownToExcel {
    public static void main(String[] args) {
        for (String arg : args) {
            System.out.println("arg = " + arg);
        }
        Path inPath = Paths.get(args[0]);
        Path outPath = Paths.get(args[1]);
        try (OutputStream os = Files.newOutputStream(outPath)){
            List<String> collect = Files.lines(inPath, StandardCharsets.UTF_8).collect(Collectors.toList());
            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet();
            for (int i = 0; i < collect.size(); i++) {
                String[] rowString = collect.get(i).split("\\|");
                Row row = sheet.createRow(i);
                for (int j = 1; j < rowString.length; j++) {
                    Cell cell = row.createCell(j);
                    cell.setCellValue(rowString[j]);
                }
            }
            workbook.setSheetName(0, outPath.getFileName().toString().split("\\.")[0]);
            workbook.write(os);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
