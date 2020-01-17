
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Test2 {
  public static void main(String[] args) throws Exception {
    String s = "크롬 : Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/32.0.2062.124 Safari/ 537.36";
    String temp = s.substring(s.indexOf("Chrome/"));
    System.out.println("temp = " + temp.substring(temp.indexOf("/")+1, temp.indexOf(".")));


    String s2 = "사파리 : Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/534.57.2 (KHTML, like Gecko) Version/5.1.7 Safari/534.57.2";
    String temp2 = s2.substring(s2.indexOf("Version/"));
    System.out.println("temp2 = " + temp2.substring(temp2.indexOf("/")+1, temp2.indexOf(".")));

    String s3 = "익스플로러 11 : Mozilla/5.0 (Windows NT 6.1; WOW64; Trident/7.0; SLCC2; .NET CLR 2.0.50727; .NET CLR 3.5.30729; .NET CLR 3.0.30729; Media Center PC 6.0; .NET4.0C; .NET4.0E; InfoPath.2; rv:11.0) like Gecko";
    System.out.println("getVersion(s3,\"Trident\") = " + getVersion(s3,"Trident"));

    String s4 = "파이어폭스 : Mozilla/5.0 (Windows NT 6.1; WOW64; rv:32.0) Gecko/20100101 Firefox/32.0";
    System.out.println("getVersion(s4,\"Firefox\") = " + getVersion(s4,"Firefox"));

//    TestClass.test();

    int num = -10;
    System.out.println(num<<2);
    System.out.println(num>>10);
    System.out.println(num>>>1);

    Object obj = new Object();
    Object obj2 = new String();
    TestClass testClass = new TestClass();
    TestClassSub1 testClass1 = new TestClassSub1();
    EnumTest enumTest = EnumTest.INSTANCE;

    System.out.println(obj instanceof Object);
    System.out.println(obj instanceof String);
    System.out.println(obj2 instanceof String);
    System.out.println(testClass instanceof TestClassSub1);
    System.out.println(testClass instanceof TestClass);
    System.out.println(testClass instanceof Object);
    System.out.println(testClass1 instanceof TestClassSub1);
    System.out.println(testClass1 instanceof TestClass);

    List<String> list = new ArrayList<>(20);
    Collections.fill(list, "a");
    list.forEach(System.out::println);
//    List<String> list2 = new LinkedList<>();
    String s1 = new String();
  }

  private static String getVersion(String value, String type) {
    String temp = value.substring(value.indexOf(type));
    return temp.substring(temp.indexOf("/")+1, temp.indexOf("."));  //버전 걸러내기
  }


}