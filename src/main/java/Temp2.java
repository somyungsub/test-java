import browserlog.Browser;
import browserlog.BrowserName;
import browserlog.BrowserUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;


public class Temp2 {

  private static long successTotal= 0;
  private static long failTotal = 0;

  public static void main(String[] args) throws IOException {
//    Stream<Path> stream = Files.list(Paths.get("/Users/myungsubso/Desktop/log/"));
//    stream.forEach(System.out::println);
//    stream = stream.sorted();
//    for (Iterator<Path> it = stream.iterator(); it.hasNext(); ) {
//      Path path = it.next();
//      System.out.println("앞의 path = " + path);
//      List<Browser> list = BrowserUtils.getLogFile(path);
//      int total = list.stream().collect(Collectors.summingInt(v -> v.getFailList().size() + v.getSuccessList().size()));
//      System.out.println("total = " + total);
//    }


//    Stream<Path> stream = Files.walk(Paths.get("/Users/myungsubso/Desktop/log/hompage1"));
//    Stream<Path> pathStream = stream.filter(path -> !Files.isDirectory(path));
//    Stream<List<Browser>> streamList = pathStream.filter(path -> !Files.isDirectory(path)).map(path -> BrowserUtils.getBrowserNameList(path));
//
//    System.out.println(" Start ");
//    Optional<List<Browser>> optionalBrowsers = streamList.reduce((browsers, browsers2) -> {
//      browsers.addAll(browsers2);
//      return browsers;
//    });
//
//
//    Map<BrowserName, List<Browser>> map = optionalBrowsers.get().stream().collect(groupingBy(Browser::getBrowserName, toList()));
//    System.out.println(" End ");
//
//    map.forEach((browserName, browsers) -> {
//      System.out.println(browserName);
//      long successCount = browsers.stream().collect(summingLong(v -> v.getSuccessCount()));
//      long failCount = browsers.stream().collect(summingLong(v -> v.getFailCount()));
//      System.out.println("failCount = " + failCount);
//      System.out.println("successCount = " + successCount);
//      failTotal += failCount;
//      successTotal += successCount;
//    });
//
//    System.out.println(" ================================== ");
//    System.out.println("successTotal = " + successTotal);
//    System.out.println("failTotal = " + failTotal);
//    System.out.println("Total = " + (successTotal + failTotal));

    //    long total = streamList.collect(summingInt(value -> value.stream().collect(summingInt(value1 -> value1.getFailList().size() + value1.getSuccessList().size()))));
//    Optional<List<Browser>> optionalBrowsers = streamList.reduce((browsers, browsers2) -> {
//      browsers.addAll(browsers2);
//      return browsers;
//    });
//    System.out.println("optionalBrowsers = " + optionalBrowsers);



//    streamList.forEach(browsers -> {
//      Map<BrowserName, Integer> map = browsers.stream().collect(groupingBy(Browser::getBrowserName, summingInt(v -> v.getSuccessList().size() + v.getFailList().size())));
//      System.out.println("map = " + map);
//
//
//      System.out.println(" ======================================================= ");
//    });

//    List<List<Browser>> list =streamList.collect(toList());
//    for (List<Browser> browsers : list) {
//      Map<BrowserName, Integer> map = browsers.stream().collect(groupingBy(Browser::getBrowserName, summingInt(v -> v.getSuccessList().size() + v.getFailList().size())));
//      System.out.println("map = " + map);
//      System.out.println(" ======================================================= ");
//    }

//    streamList.forEach(browsers -> {
//      browsers.stream().forEach(browser -> {
//        long fail = browser.getFailList().size();
//        long success = browser.getSuccessList().size();
//        System.out.printf("Browser : %s\t\tfail : %d\t\tsuccess : %d\t\t total : %d \n", browser.getBrowserName(), fail, success, fail + success);
//      });
//      System.out.println(" ====================================================================================================== ");
//    });

  }

}
