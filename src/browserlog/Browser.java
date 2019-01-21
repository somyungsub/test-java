package browserlog;

import java.util.ArrayList;
import java.util.List;

public class Browser {
  private BrowserName browserName;
  private List<String> successList;
  private List<String> failList;

  public Browser buildName(BrowserName browserName) {
    this.browserName = browserName;
    return this;
  }
  public Browser buildFail(List<String> failList) {
    this.failList = failList;
    return this;
  }

  public Browser buildSuccess(List<String> successList) {
    this.successList = successList;
    return this;
  }

  public List<String> getAllList() {
    List<String> list = new ArrayList<>();
    list.addAll(successList);
    list.addAll(failList);
    return list;
  }

  public BrowserName getBrowserName() {
    return browserName;
  }

  public void setBrowserName(BrowserName browserName) {
    this.browserName = browserName;
  }

  public List<String> getSuccessList() {
    return successList;
  }

  public void setSuccessList(List<String> successList) {
    this.successList = successList;
  }

  public List<String> getFailList() {
    return failList;
  }

  public void setFailList(List<String> failList) {
    this.failList = failList;
  }
}
