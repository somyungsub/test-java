package browserlog;

public enum BrowserName {

  GOOGLE("GOOGLE") {
    @Override
    boolean isTls(String s) {
      return Double.valueOf(s).compareTo(30.0) >= 0;
    }
  },
  MSIE("MSIE") {
    @Override
    boolean isTls(String s) {
      return Double.valueOf(s).compareTo(11.0) >= 0;
    }
  },
  FIREFOX("FIREFOX") {
    @Override
    boolean isTls(String s) {
      return Double.valueOf(s).compareTo(27.0) >= 0;
    }
  },
  SAFARI("SAFARI") {
    @Override
    boolean isTls(String s) {
      return Double.valueOf(s).compareTo(7.0) >= 0;
    }
  },
  MOBILE_SAFARI("MOBILE_SAFARI"){
    @Override
    boolean isTls(String s) {
      return Double.valueOf(s).compareTo(5.0) >= 0;
    }
  },
  ETC("ETC") {
    @Override
    boolean isTls(String s) {
      return false;
    }
  };

  abstract boolean isTls(String s); // TLS 가능여부 판단

  private String value;

  BrowserName(String value) {
    this.value = value;
  }

  @Override
  public String toString() {
    return value;
  }
}
