package browserlog;

public class BrowserUtils {

  // 브라우저 이름 얻기 (key로 사용)
  public static BrowserName getBrowserName(String s) {
    if (s.contains("Chrome")) {
      return BrowserName.GOOGLE;
    } else if (s.contains("Safari")) {
      return BrowserName.SAFARI;
    } else if (s.contains("Firefox")) {
      return BrowserName.FIREFOX;
    } else {
      return BrowserName.MSIE;  // TODO 오페라등 빈도 낮은 나머지 브라우저들 MSIE로 처리 -> 차후 요건발생시 수정
    }
  }

  // TLS 사용가능 여부 판단
  public static boolean isTls(String s) {
    BrowserName browserName = getBrowserName(s);
    return browserName.isTls(getTlsStandard(browserName, s));
  }

  // 버전기준 얻기
  public static String getTlsStandard(BrowserName browserName, String dataValue) {
    switch (browserName) {
      case GOOGLE:
        return getVersion(dataValue, "Chrome");
      case SAFARI:
        return getVersion(dataValue, "Version");
      case FIREFOX:
        return getVersion(dataValue, "Firefox");
      case MSIE:
        return getVersion(dataValue, "Trident");
      default:
        return getVersion(dataValue, "Null");
    }
  }

  // 버전 번호 거르기
  public static String getVersion(String dataValue, String versionCheckValue) {
    int index = dataValue.indexOf(versionCheckValue);
    if (index < 0) {
      return String.valueOf(index);
    }
    String temp = dataValue.substring(index);                         //  버전체크 하기위한 부분 거르기
    return temp.substring(temp.indexOf("/") + 1, temp.indexOf("."));  //  최초 버전 숫자 얻기 (ex: 17.1.2 -> 17)
  }

}
