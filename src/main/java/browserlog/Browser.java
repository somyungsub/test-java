package browserlog;

public class Browser {
  private BrowserName browserName;
  private OperationSystemName operationSystemName;
  private String userAgent;
//  private List<String> successList;
//  private List<String> failList;
  private long successCount;
  private long failCount;

  public Browser buildName(BrowserName browserName) {
    this.browserName = browserName;
    return this;
  }

  public Browser buildOS(OperationSystemName operationSystemName) {
    this.operationSystemName = operationSystemName;
    return this;
  }

  public Browser buildUserAgent(String userAgent) {
    this.userAgent = userAgent;
    return this;
  }

  public Browser buildSuccessCount(long successCount) {
    this.successCount = successCount;
    return this;
  }

  public Browser buildFailCount(long failCount) {
    this.failCount = failCount;
    return this;
  }



//  public Browser buildFail(List<String> failList) {
//    this.failList = failList;
//    return this;
//  }
//
//  public Browser buildSuccess(List<String> successList) {
//    this.successList = successList;
//    return this;
//  }
//
//  public List<String> getAllList() {
//    List<String> list = new ArrayList<>();
//    list.addAll(successList);
//    list.addAll(failList);
//    return list;
//  }

  public BrowserName getBrowserName() {
    return browserName;
  }

  public OperationSystemName getOperationSystemName() {
    return operationSystemName;
  }

  public String getUserAgent() {
    return userAgent;
  }

  public long getFailCount() {
    return failCount;
  }

  public long getSuccessCount() {
    return successCount;
  }

  public long getTotal() {
    return successCount + failCount;
  }

  //  public List<String> getSuccessList() {
//    return successList;
//  }
//
//  public List<String> getFailList() {
//    return failList;
//  }

//  @Override
//  public String toString() {
//    StringBuffer sb = new StringBuffer();
//    sb.append(browserName).append(System.lineSeparator());
//    sb.append("============== Fail ==============").append(System.lineSeparator());
//    failList.forEach(s->sb.append(s).append(System.lineSeparator()));
//    sb.append("============== success ==============").append(System.lineSeparator());
//    successList.forEach(s->sb.append(s).append(System.lineSeparator()));
//    return sb.toString();
//  }
}
