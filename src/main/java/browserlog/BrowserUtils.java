package browserlog;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

public class BrowserUtils {

    public static List<Browser> getBrowserList(Path path) {
    System.out.println("path = " + path);
    try (BufferedReader br = Files.newBufferedReader(path, StandardCharsets.ISO_8859_1)) {

      Map<BrowserName, Map<OperationSystemName, List<String>>> browserMap
              = getNameOsGroup(br.lines(), BrowserName::getBrowserName, OperationSystemName::getOperationSystemName);

      List<Browser> browserList = new ArrayList<>();
      browserMap.forEach((name1, typeMap) -> {
        typeMap.forEach((name2, strings) -> {
          Map<Boolean, List<String>> map = typeMap.get(name2).stream().collect(partitioningBy(s -> {
            BrowserName browserName = BrowserName.getBrowserName(s);
            return browserName.isTls(browserName.getVersionCheck(s));
          }, toList()));

          browserList.add(new Browser().buildName(name1).buildOS(name2).buildFailCount(map.get(false).size()).buildSuccessCount(map.get(true).size()));
        });
      });

      return browserList;
    } catch (IOException e) {
      e.printStackTrace();
    }
    return Collections.EMPTY_LIST;
  }



  private static <T,S> Map<T, Map<S, List<String>>> getNameOsGroup( Stream<String> stream
                                                                  , Function<String,T> func1
                                                                  , Function<String,S> func2) {

    return stream.collect(groupingBy(func1, groupingBy(func2, toList())));
  }



//  public static <T,S> Map<T, Map<S, List<String>>> getBrowserList2( Path path
//          , Function<String,T> func1
//          , Function<String,S> func2) {
//    System.out.println("path = " + path);
//    try (BufferedReader br = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
//      Map<T, Map<S, List<String>>> browserMap = getNameOsGroup(br.lines(), func1, func2);
//      return browserMap;
//    } catch (IOException e) {
//      e.printStackTrace();
//    }
//    return Collections.EMPTY_MAP;
//  }



//  public static <T,K,S> List<T> test (Map<K, Map<S, List<String>>> browserMap, Supplier<T> supplier) {
//    List<T> browserList = new ArrayList<>();

//    browserMap.forEach((k, v) -> {
//      v.forEach((o, o2) -> {
//        Map<Boolean, List<String>> map = v.get(o).stream().collect(partitioningBy(BrowserUtils::isTls, toList()));
//        browserList.add(supplier.get());
//        Browser browser = new Browser();
//        browser.buildOS(o instanceof OperationSystemName ? );
//        browserList.add(new Browser().buildOS(k).buildName(o).buildFailCount(map.get(false).size()).buildSuccessCount(map.get(true).size()));
//      });
//    });
//
//    return browserList;
//  }

//  public static Supplier<Browser> getBrowser() {
//      Supplier<Browser> supplier = Browser::new;
//      return supplier;
//  }


  /*
    브라우저별-> OS별 리스트 얻기
   */
//  private static Map<BrowserName, Map<OperationSystemName, List<String>>> getNameOsGroup(Stream<String> stream) {
//    return stream.collect(groupingBy(s -> getBrowserName(s)
//            , groupingBy(o -> getOperationSystemName(o)
//                    , toList())));
//  }

  /*
    브라우저별 리스트 얻기
   */
//  private static Map<BrowserName, List<String>> getBrowserNameGroup(Stream<String> stream) {
//    return stream.collect(groupingBy(s -> getBrowserName(s), toList()));
//  }


  /*
    브라우저 이름만
   */
//  public static List<Browser> getBrowserNameList(Path path) {
//    System.out.println("path = " + path);
//    try (BufferedReader br = Files.newBufferedReader(path, StandardCharsets.ISO_8859_1)) {
//
//      // 브라우저별
//      Map<BrowserName, List<String>> browserMap = getBrowserNameGroup(br.lines());
//
//      // 브라우저별 -> 기준별(T/F) - 클래스 사용
//      List<Browser> browserList = new ArrayList<>();
//      for (BrowserName browserName : browserMap.keySet()) {
//        Map<Boolean, List<String>> map = browserMap.get(browserName).stream()
//                .collect(partitioningBy(BrowserUtils::isTls, toList()));
////        browserList.add(new Browser().buildName(browserName).buildFail(map.get(false)).buildSuccess(map.get(true)));
//        browserList.add(new Browser().buildName(browserName).buildFailCount(map.get(false).size()).buildSuccessCount(map.get(true).size()));
//      }
//      return browserList;
//    } catch (IOException e) {
//      e.printStackTrace();
//    }
//    return Collections.EMPTY_LIST;
//  }


}
