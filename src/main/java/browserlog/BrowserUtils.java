package browserlog;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

public class BrowserUtils {

    public static List<Browser> getBrowserList(Path path) {
    System.out.println("path = " + path);
    try (BufferedReader br = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {

      Map<BrowserName, Map<OperationSystemName, List<String>>> browserMap
              = getNameOsGroup(br.lines(), BrowserUtils::getBrowserName, BrowserUtils::getOperationSystemName);

      List<Browser> browserList = new ArrayList<>();
      browserMap.forEach((name1, typeMap) -> {
        typeMap.forEach((name2, strings) -> {
          Map<Boolean, List<String>> map = typeMap.get(name2).stream().collect(partitioningBy(BrowserUtils::isTls, toList()));
          browserList.add(new Browser().buildOS(name2).buildName(name1).buildFailCount(map.get(false).size()).buildSuccessCount(map.get(true).size()));
        });
      });

      return browserList;
    } catch (IOException e) {
      e.printStackTrace();
    }
    return Collections.EMPTY_LIST;
  }


  public static <T,S> Map<T, Map<S, List<String>>> getBrowserList2( Path path
                                                                  , Function<String,T> func1
                                                                  , Function<String,S> func2) {
    System.out.println("path = " + path);
    try (BufferedReader br = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
      Map<T, Map<S, List<String>>> browserMap = getNameOsGroup(br.lines(), func1, func2);
      return browserMap;
    } catch (IOException e) {
      e.printStackTrace();
    }
    return Collections.EMPTY_MAP;
  }

  private static <T,S> Map<T, Map<S, List<String>>> getNameOsGroup( Stream<String> stream
                                                                  , Function<String,T> func1
                                                                  , Function<String,S> func2) {

    return stream.collect(groupingBy(func1, groupingBy(func2, toList())));
  }


  public static <T,K,S> List<T> test (Map<K, Map<S, List<String>>> browserMap, Supplier<T> supplier) {
    List<T> browserList = new ArrayList<>();

    browserMap.forEach((k, v) -> {
      v.forEach((o, o2) -> {
        Map<Boolean, List<String>> map = v.get(o).stream().collect(partitioningBy(BrowserUtils::isTls, toList()));
        browserList.add(supplier.get());
//        Browser browser = new Browser();
//        browser.buildOS(o instanceof OperationSystemName ? );
//        browserList.add(new Browser().buildOS(k).buildName(o).buildFailCount(map.get(false).size()).buildSuccessCount(map.get(true).size()));
      });
    });

    return browserList;
  }

  public static Supplier<Browser> getBrowser() {
      Supplier<Browser> supplier = Browser::new;
      return supplier;
  }

  /*
    OS 이름 얻기 (key로 사용)
   */
  public static OperationSystemName getOperationSystemName(String s) {
    if (s.contains("Macintosh")) {
      return OperationSystemName.MACOS;
    } else if (s.contains("iPhone") || s.contains("iPad")) {
      return OperationSystemName.IOS;
    } else if (s.contains("Android")) {
      return OperationSystemName.ANDROID;
    } else if (s.contains("Linux")) {
      return OperationSystemName.LINUX;
    } else if (s.contains("Windows")) {
      return OperationSystemName.WINDOWS;
    } else {
      return OperationSystemName.ETC;    // TODO 이외 빈도 낮은 OS 기타 처리 (확인불가)
    }
  }

  /*
    브라우저 이름 얻기 (key로 사용)
   */
  public static BrowserName getBrowserName(String s) {
    if (s.contains("Chrome/") || s.contains("CriOS/")) {
      return BrowserName.GOOGLE;
    } else if (s.contains("iPad") || s.contains("iPhone")) {
      return BrowserName.MOBILE_SAFARI;
    } else if (s.contains("Macintosh")) {
      return BrowserName.SAFARI;
    } else if (s.contains("Firefox/")) {
      return BrowserName.FIREFOX;
    } else if (s.contains("MSIE") || s.contains("Trident/")) {
      return BrowserName.MSIE;
    } else {
      return BrowserName.ETC;  // TODO 오페라등 빈도 낮은 나머지 브라우저들 MSIE로 처리 -> 차후 요건발생시 수정
    }
  }

  /*
    TLS 사용가능 여부 판단
   */
  public static boolean isTls(String s) {
    BrowserName browserName = getBrowserName(s);
    return browserName.isTls(getTlsStandard(browserName, s));
  }

  /*
    버전기준 얻기
   */
  public static String getTlsStandard(BrowserName browserName, String dataValue) {
    switch (browserName) {
      case GOOGLE:
        String version = getVersion(dataValue, "Chrome/");
        return version.compareTo("0") < 0 ? getVersion(dataValue, "CriOS/") : version ;
      case SAFARI:
      case MOBILE_SAFARI:
        return getVersion(dataValue, "Version/");
      case FIREFOX:
        return getVersion(dataValue, "Firefox/");
      case MSIE:
        if(dataValue.contains("MSIE")){
          return getVersion(dataValue, "MSIE");
        }
        return getVersion(dataValue, "Trident/");
      default:
        return "-1";
    }
  }

  /*
    버전 번호 자르기
   */
  private static String getVersion(String dataValue, String versionCheckValue) {
    int index = dataValue.indexOf(versionCheckValue);
    if (index < 0) {
      return String.valueOf(index);
    }

    //  버전체크 하기위한 부분 자르기
    String temp = dataValue.substring(index);
    int index2 = temp.indexOf(".");

    // MSIE 11 인 경우 추가 처리
    if (versionCheckValue.equals("MSIE") && temp.contains("MSIE 11") || versionCheckValue.equals("Trident/")) {
      temp = temp.substring(temp.indexOf("rv:"));
      return temp.substring(temp.indexOf(":") + 1, temp.indexOf("."));
    }

    // 버전에 소수점이 없는 경우
    if (index2 < 0) {
      System.out.println("dataValue = " + dataValue);
      System.out.println("versionCheckValue = " + versionCheckValue);
      System.out.println("temp = " + temp);
      temp = temp.substring(temp.indexOf("/") + 1, temp.length() - 1);
      return temp.replaceAll("[^0-9]", "");
    }

    // 버전에 소수점이 있는 경우 -> 최초 버전 숫자 얻기 (ex: 17.1.2 -> 17)
    return  versionCheckValue.equals("MSIE")
            ? temp.substring(temp.indexOf(" ") + 1, index2)
            : temp.substring(temp.indexOf("/") + 1, index2);
  }




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
