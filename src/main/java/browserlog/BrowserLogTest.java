package browserlog;


import com.sun.org.apache.xpath.internal.operations.Bool;
import excel.BrowserData;
import excel.ExcelJxlsUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;


public class BrowserLogTest {

  private static long successTotal = 0;
  private static long failTotal = 0;

  private static Map<OperationSystemName, Map<Boolean,Long>> osData = new HashMap<>();
  private static Map<BrowserName, Map<Boolean,Long>> nameData = new HashMap<>();


  public static void main(String[] args) throws IOException {

    // 디렉터리 제외한 파일Path만 얻기
    Stream<Path> stream = Files.walk(Paths.get("/Users/myungsubso/Desktop/log/hompage1/access.log_20181203"));
    Stream<Path> pathStream = stream.filter(path -> !Files.isDirectory(path));


    // 1. 가공데이터 얻기
//    Stream<List<Browser>> streamList
//            = pathStream.map(path -> BrowserUtils.getBrowserList2(path,BrowserUtils::getBrowserName, BrowserUtils::getOperationSystemName))
//                        .map(browserMap -> BrowserUtils.test(browserMap, );

    Stream<List<Browser>> streamList = pathStream.map(path -> BrowserUtils.getBrowserList(path));

    // 2. 데이터 Optional화
    Optional<List<Browser>> optionalBrowsers
            = streamList.reduce((browsers, browsers2) -> {
      browsers.addAll(browsers2);
      return browsers;
    });

    executeBrowserNameAndOS(optionalBrowsers);  // Name, OS
    executeBrowserName(optionalBrowsers);       // Name
    executeBrowserOS(optionalBrowsers);       // OS

    List<BrowserData> osList = getBrowserData(osData);
    List<BrowserData> browserList = getBrowserData(nameData);

    osList.stream().forEach(System.out::println);
    browserList.stream().forEach(System.out::println);


    ExcelJxlsUtils.makeExcel(browserList,osList);

    // 성공, 실패, 전체 횟수
    System.out.println(" ================================== ");
    System.out.println("successTotal = " + successTotal);
    System.out.println("failTotal = " + failTotal);
    System.out.println("Total = " + (successTotal + failTotal));

  }
  private static <T> List<BrowserData> getBrowserData(Map<T,Map<Boolean, Long>> map) {
    List<BrowserData> browserList = new ArrayList<>();
    map.forEach((name, longs) -> {

      BrowserData browserData = new BrowserData();

      String s = name.toString();
      if (name instanceof OperationSystemName) {
        browserData.setOsName(s);
        browserData.setBrowserName("");
      } else {
        browserData.setOsName("");
        browserData.setBrowserName(s);
      }

      long failCount = longs.get(false);
      long successCount = longs.get(true);
      browserData.setFailCount(failCount);
      browserData.setSuccessCount(successCount);
      browserData.setRate(100 * successCount/(failCount + successCount));
      browserList.add(browserData);
    });
    return browserList;
  }

  private static void executeBrowserName(Optional<List<Browser>> optionalBrowsers) {
    Map<BrowserName, List<Browser>> map
            = optionalBrowsers.get().stream().collect(groupingBy(Browser::getBrowserName, toList()));

    map.forEach((browserName, browsers) -> {
      Map<Boolean, Long> map2 = new HashMap<>();
      map2.putIfAbsent(false, calculationFail(browsers));
      map2.putIfAbsent(true, calculationSuccess(browsers));
      nameData.putIfAbsent(browserName, map2);
    });
  }


  private static void executeBrowserOS(Optional<List<Browser>> optionalBrowsers) {
    Map<OperationSystemName, List<Browser>> map
            = optionalBrowsers.get().stream().collect(groupingBy(Browser::getOperationSystemName, toList()));

    map.forEach((osName, browsers) -> {
      Map<Boolean, Long> map2 = new HashMap<>();
      map2.putIfAbsent(false, calculationFail(browsers));
      map2.putIfAbsent(true, calculationSuccess(browsers));
      osData.putIfAbsent(osName, map2);
    });
  }

  private static void executeBrowserNameAndOS(Optional<List<Browser>> optionalBrowsers) {
    Map<BrowserName, Map<OperationSystemName, List<Browser>>> map
            = optionalBrowsers.get().stream().collect(groupingBy(Browser::getBrowserName,
            groupingBy(Browser::getOperationSystemName, toList())));

    map.forEach((browserName, browsers) -> {
      System.out.println(browserName);
      browsers.forEach((operationSystemName, browsers1) -> {
        System.out.println("\t" + operationSystemName);
        long fail = calculationFail(browsers1);
        long success = calculationSuccess(browsers1);
      });
    });
  }


  private static long calculationSuccess(List<Browser> browsers) {
    long successCount = browsers.stream().collect(summingLong(v -> v.getSuccessCount()));
//    System.out.println("\t\tsuccessCount = " + successCount);
//    successTotal += successCount;
    return successCount;
  }

  private static long calculationFail(List<Browser> browsers) {
    long failCount = browsers.stream().collect(summingLong(v -> v.getFailCount()));
//    System.out.println("\t\tfailCount = " + failCount);
//    failTotal += failCount;
    return failCount;
  }


//  private static void executeBrowserNameAndOsAndType(Optional<List<Browser>> optionalBrowsers) {
//    Map<BrowserName, Map<OperationSystemName, Map<String, List<Browser>>>> map
//            = optionalBrowsers.get().stream().collect(groupingBy(Browser::getBrowserName,
//                                                                  groupingBy(Browser::getOperationSystemName,
//                                                                            groupingBy(Browser::getUserAgent,toList()))));
//
//    map.forEach((browserName, browsers) -> {
//      System.out.println(browserName);
//      browsers.forEach((operationSystemName, browsers1) -> {
//        System.out.println("\t"+operationSystemName);
//        browsers1.forEach((s, browsers2) -> {
//          System.out.println("s = " + s);
//          countPrint(browsers2);
//        });
//      });
//    });
//  }


}
