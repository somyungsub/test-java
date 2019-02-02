public class BizException extends Exception {

  protected String code;
  protected String msg;
  protected Throwable rootCaouse;

  public BizException(String msg) {
    this(msg, null, null);
  }

  public BizException(String msg, Throwable rootCaouse) {
    this(msg, null, rootCaouse);
  }

  public BizException(String msg, String code) {
    this(msg, code, null);
  }


  public BizException(String message, String code, Throwable rootCause) {
    super(rootCause);

  }
}
