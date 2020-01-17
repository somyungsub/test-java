package excel;

import org.jxls.common.Context;
import org.jxls.util.JxlsHelper;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class ExcelJxlsUtils {

  public static void makeExcel(List<BrowserData> list, List<BrowserData> list2) {
    try (InputStream is = ExcelJxlsUtils.class.getResourceAsStream("/input_log_data.xlsx");
         OutputStream os = Files.newOutputStream(Paths.get("src/main/resources/out_log_data.xlsx"))) {

      Context context = new Context();
      context.putVar("list", list);
      context.putVar("list2", list2);
      context.putVar("totalCellUpdater", new JxlsCommand());
      JxlsHelper.getInstance().processTemplate(is, os, context);

    } catch (IOException e) {
      e.printStackTrace();
    }
  }


//  public static void makeExcel(List<BrowserData> list, String sheetName) {
//
//    try (InputStream is = ExcelJxlsUtils.class.getResourceAsStream("/input_log_data.xlsx");
//         OutputStream os = Files.newOutputStream(Paths.get("src/main/resources/out_log_data.xlsx"))) {
//      System.out.println("is = " + is);
//      System.out.println("out = " + os);
//
//
//      Context context = new Context();
//      context.putVar("list", list);
//
//      JxlsHelper jxlsHelper = JxlsHelper.getInstance();
//      Workbook workbook = WorkbookFactory.create(is);
//      workbook.createSheet(sheetName);
//      PoiTransformer transformer = PoiTransformer.createTransformer(workbook);
//      transformer.setOutputStream(os);
//      jxlsHelper.processTemplate(context,transformer);
//
////      JxlsHelper.getInstance().processTemplateAtCell(is, os, context, sheetName);
//
//    } catch (IOException e) {
//      e.printStackTrace();
//    } catch (InvalidFormatException e) {
//      e.printStackTrace();
//    }
//  }
}
