package browserlog;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import static java.util.stream.Collectors.*;

public class BrowserLogTest {

  public static void main(String[] args) {
    Path path = Paths.get("src", "browserlog/test.txt");
    try (BufferedReader br = Files.newBufferedReader(path)) {

      // 브라우저별
      Map<BrowserName, List<String>> browserMap = br.lines().collect(groupingBy(BrowserUtils::getBrowserName, toList()));

      // 전체건수
      int total = browserMap.values().stream().collect(summingInt(List::size));

      // 브라우저별 -> 기준별(T/F) - 클래스 사용
      List<Browser> browserList = new ArrayList<>();
      for (BrowserName browserName : browserMap.keySet()) {
        Map<Boolean, List<String>> map = browserMap.get(browserName).stream()
                                                   .collect(partitioningBy(BrowserUtils::isTls, toList()));
        browserList.add(new Browser().buildName(browserName).buildFail(map.get(false)).buildSuccess(map.get(true)));
      }


      // 브라우저별 -> 기준별(T/F)
//      Map<BrowserName, Map<Boolean, List<String>>> standardMap = new HashMap<>();
//      for (BrowserName browserName : browserMap.keySet()) {
//        Map<Boolean, List<String>> map = browserMap.get(browserName).stream().collect(partitioningBy(BrowserUtils::isTls, toList()));
//        standardMap.put(browserName, map);
//      }

//      standardMap.forEach((browserName, booleanListMap) -> {
//        System.out.println("browserName = " + browserName);
//        booleanListMap.forEach((aBoolean, strings) -> {
//          System.out.println("aBoolean = " + aBoolean);
//          strings.forEach(System.out::println);
//        });
//      });


    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
