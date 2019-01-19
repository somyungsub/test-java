import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.*;

public class BrowserLogTest {
  public enum BrowserName{
    GOOGLE,
    MSIE,
    FIREFOX,
    SAFARI
  }

  public static void main(String[] args) {
    Path path = Paths.get("src", "test.txt");
    try (BufferedReader br = Files.newBufferedReader(path)) {

      // 브라우저별로 모으기
      Map<BrowserName, List<String>> browserMap = br.lines().collect(groupingBy(s->getBrowserName(s), toList()));

      int total = browserMap.values().stream().collect(summingInt(List::size)); // 전체건수
//      browserMap.forEach((s, strings) -> {
//        System.out.println("key : " + s);
//        strings.forEach(System.out::println);
//      });

//      browserMap.entrySet().stream().collect(partitioningBy(BrowserLogTest::isTLS));


    } catch (IOException e) {
      e.printStackTrace();
    }

  }


  private static boolean isTls(Map.Entry<String, List<String>> entry) {
    String key = entry.getKey();
    for (String value : entry.getValue()) {
      if ("Chrome".equals(key)) {
        return value.contains("Chrome/32");
      } else if ("Safari".equals(key)) {
        return value.contains("Version/5.1");
      } else if ("Firefox".equals(key)) {
        return value.contains("Firefox/32.0");
      } else {
        return value.contains("Trident/7.0");
      }
    }
    return false;
  }

  // 브라우저 이름 얻기 (key로 사용)
  private static BrowserName getBrowserName(String s) {
    if(s.contains("Chrome")) {
      return BrowserName.GOOGLE;
    } else if (s.contains("Safari")) {
      return BrowserName.SAFARI;
    } else if (s.contains("Firefox")) {
      return BrowserName.FIREFOX;
//    } else if (s.contains("Opera")) {
//      return "Opera";
    } else {
      return BrowserName.MSIE;
    }
  }
}
