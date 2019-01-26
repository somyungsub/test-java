package excel;

public class BrowserData {
  private String browserName;
  private String osName;
  private long failCount;
  private long successCount;
  private long rate;

  public BrowserData() {
  }

  public BrowserData(String browserName, String osName, long failCount, long successCount) {
    this.browserName = browserName;
    this.osName = osName;
    this.failCount = failCount;
    this.successCount = successCount;
  }

  public String getBrowserName() {
    return browserName;
  }

  public void setBrowserName(String browserName) {
    this.browserName = browserName;
  }

  public String getOsName() {
    return osName;
  }

  public void setOsName(String osName) {
    this.osName = osName;
  }

  public long getFailCount() {
    return failCount;
  }

  public void setFailCount(long failCount) {
    this.failCount = failCount;
  }

  public long getSuccessCount() {
    return successCount;
  }

  public void setSuccessCount(long successCount) {
    this.successCount = successCount;
  }

  public long getRate() {
    return rate;
  }

  public void setRate(long rate) {
    this.rate = rate;
  }

  @Override
  public String toString() {
    return "BrowserData{" +
            "browserName='" + browserName + '\'' +
            ", osName='" + osName + '\'' +
            ", failCount=" + failCount +
            ", successCount=" + successCount +
            ", rate=" + rate +
            '}';
  }
}
