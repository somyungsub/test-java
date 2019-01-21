package browserlog;

public enum BrowserName {

  GOOGLE {
    @Override
    boolean isTls(String s) {
      return Double.valueOf(s).compareTo(30.0) >= 0;
    }
  },
  MSIE {
    @Override
    boolean isTls(String s) {
      return Double.valueOf(s).compareTo(7.0) >= 0;
    }
  },
  FIREFOX {
    @Override
    boolean isTls(String s) {
      return Double.valueOf(s).compareTo(27.0) >= 0;
    }
  },
  SAFARI {
    @Override
    boolean isTls(String s) {
      return Double.valueOf(s).compareTo(7.0) >= 0;
    }
  };

  abstract boolean isTls(String s); // TLS 가능여부 판단
}
