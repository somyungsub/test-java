package browserlog;

public enum BrowserName {

  Chrome("Chrome") {
    @Override
    String getVersionCheck(String dataValue) {
      if (dataValue.contains("Chrome/")) {
        return getVersion(dataValue, "Chrome/");
      }
      return getVersion(dataValue, "CriOS/");
    }

    @Override
    boolean isTls(String s) {
      return Double.valueOf(s).compareTo(30.0) >= 0;
    }
  },

  MSIE("MSIE") {
    @Override
    String getVersionCheck(String dataValue) {
      if (dataValue.contains("MSIE")) {
        return getVersion(dataValue, "MSIE");
      }
      return getVersion(dataValue, "Trident/");
    }

    @Override
    boolean isTls(String s) {
      return Double.valueOf(s).compareTo(11.0) >= 0;
    }
  },

  FIREFOX("FIREFOX") {
    @Override
    String getVersionCheck(String dataValue) {
      return getVersion(dataValue, "Firefox/");
    }

    @Override
    boolean isTls(String s) {
      return Double.valueOf(s).compareTo(27.0) >= 0;
    }
  },

  SAFARI("SAFARI") {
    @Override
    String getVersionCheck(String dataValue) {
      if (dataValue.contains("Version/")) {
        return getVersion(dataValue, "Version/");
      }
      int idx = dataValue.indexOf("Mac OS X ");
      if (idx < 0) {
        return "-1";
      }
      String temp = dataValue.substring(idx);
      temp = temp.substring(temp.indexOf("X") + 2, temp.lastIndexOf("_"));
      int version = Integer.parseInt(temp.substring(temp.indexOf("_") + 1));
      return version >= 9 ? String.valueOf(7) : String.valueOf(-1);
    }

    @Override
    boolean isTls(String s) {
      return Double.valueOf(s).compareTo(7.0) >= 0;
    }
  },

  MOBILE_SAFARI("MOBILE_SAFARI") {
    @Override
    String getVersionCheck(String dataValue) {
      if (dataValue.contains("iPhone OS")) {
        String temp = dataValue.substring(dataValue.indexOf("iPhone OS"));
        return temp.substring(temp.indexOf("OS") + 3, temp.indexOf("_"));
      }

      if (dataValue.contains("CPU OS")) {
        String temp = dataValue.substring(dataValue.indexOf("CPU OS"));
        return temp.substring(temp.indexOf("OS") + 3, temp.indexOf("_"));
      }

      return getVersion(dataValue, "Version/");
    }

    @Override
    boolean isTls(String s) {
      return Double.valueOf(s).compareTo(5.0) >= 0;
    }
  },

  ETC("ETC") {
    @Override
    String getVersionCheck(String dataValue) {
      return "-1";
    }

    @Override
    boolean isTls(String s) {
      return false;
    }
  };

  // TLS 가능여부 판단
  abstract boolean isTls(String s);

  // 브라우저별 버전 체크 판단
  abstract String getVersionCheck(String dataValue);


  // 브라우저 이름
  private final String browserName;

  BrowserName(String browserName) {
    this.browserName = browserName;
  }

  @Override
  public String toString() {
    return browserName;
  }

  /*
    분류 하기
    - 팩터리 메서드
   */
  public static BrowserName getBrowserName(String dataValue) {
    if (dataValue.contains("Chrome/") || dataValue.contains("CriOS/")) {
      return BrowserName.Chrome;
    } else if (dataValue.contains("Firefox/")) {
      return BrowserName.FIREFOX;
    } else if (dataValue.contains("like Mac OS X")) {
      return BrowserName.MOBILE_SAFARI;
    } else if (dataValue.contains("Macintosh")) {
      return BrowserName.SAFARI;
    } else if (dataValue.contains("MSIE") || dataValue.contains("Trident/")) {
      return BrowserName.MSIE;
    } else {
      return BrowserName.ETC;  // TODO 오페라등 빈도 낮은 나머지 브라우저들 MSIE로 처리 -> 차후 요건발생시 수정
    }
  }


  /*
    버전 번호 자르기
   */
  private static String getVersion(String dataValue, String versionCheckValue) {

    int index = dataValue.indexOf(versionCheckValue);

    if (index < 0) {
        System.out.println("dataValue = " + dataValue);
        System.out.println("versionCheckValue = " + versionCheckValue);
      return String.valueOf(index);
    }


    //  버전체크 하기위한 부분 자르기
    String temp = dataValue.substring(index);
    int index2 = temp.indexOf(".");

    // MSIE 인 경우 추가 처리
    if ((versionCheckValue.equals("MSIE") && temp.contains("MSIE "))
            || (versionCheckValue.equals("Trident/") && temp.contains("Trident/"))) {

      if (temp.contains("rv:")) {
        temp = temp.substring(temp.indexOf("rv:"));
        return temp.substring(temp.indexOf(":") + 1, temp.indexOf("."));
      }

      if (temp.contains("MSIE 11")) {
        return "11";
      }

      if (temp.contains("MSIE ")) {
        return temp.substring(temp.indexOf(" ") + 1, index2);
      }


      temp = temp.substring(temp.indexOf("Trident/"));
      if (temp.contains("Trident/7")) {
        return "11";
      }

      return temp.substring(temp.indexOf("/") + 1, temp.indexOf("."));
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
    return temp.substring(temp.indexOf("/") + 1, index2);
  }
}
