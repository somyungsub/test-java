package browserlog;

public enum OperationSystemName {
  MACOS("MACOS"),
  IOS("IOS"),
  ANDROID("ANDROID"),
  WINDOWS("WINDOWS"),
  LINUX("LINUX"),
  ETC("ETC")
  ;



  private String value;

  OperationSystemName(String value) {
    this.value = value;
  }

  @Override
  public String toString() {
    return value;
  }

  /*
    data에 따른 분류
    - 팩토리 메서드
   */
  public static OperationSystemName getOperationSystemName(String dataValue) {
    if (dataValue.contains("Macintosh")) {
      return OperationSystemName.MACOS;
    } else if (dataValue.contains("iPhone") || dataValue.contains("iPad")) {
      return OperationSystemName.IOS;
    } else if (dataValue.contains("Android")) {
      return OperationSystemName.ANDROID;
    } else if (dataValue.contains("Linux")) {
      return OperationSystemName.LINUX;
    } else if (dataValue.contains("Windows")) {
      return OperationSystemName.WINDOWS;
    } else {
      return OperationSystemName.ETC;    // TODO 이외 빈도 낮은 OS 기타 처리 (확인불가)
    }
  }

}
