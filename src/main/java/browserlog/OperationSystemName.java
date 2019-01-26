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

}
