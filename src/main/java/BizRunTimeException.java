public class BizRunTimeException extends RuntimeException {

  protected String code;
  protected String msg;
  protected Throwable rootCaouse;

  public BizRunTimeException(String msg) {
    this(msg, null, null);
  }

  public BizRunTimeException(String msg, Throwable rootCaouse) {
    this(msg, null, rootCaouse);
  }

  public BizRunTimeException(String msg, String code) {
    this(msg, code, null);
  }


  public BizRunTimeException(String message, String code, Throwable rootCause) {
    super(rootCause);

  }
}
