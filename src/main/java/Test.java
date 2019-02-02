import java.io.BufferedReader;
import java.io.EOFException;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.ZipException;

public class Test {
  public static void main(String[] args) throws BizException {
//        StringBuilder sb = new StringBuilder("AaBasdQqerrkfkasd".toUpperCase());
    StringBuilder sb = new StringBuilder("AaSsssIiiiQqweg".toUpperCase());
    Map<String, Integer> map = new HashMap<>();
    for (int i = 0; i < sb.length(); i++) {
      String s = String.valueOf(sb.charAt(i));
      if (map.containsKey(s)) {
        map.put(s, map.get(s) + 1);
      } else {
        map.put(s, 1);
      }
    }

//    final String encoding = "UTF-8";
//    long start = System.nanoTime();
//    for (int i = 0; i < 1000000; i++) {
//      String s = "contect" + encoding;
//      String s = "contect".concat(encoding);
//    }
//    System.out.println("걸린시간 = " + ((System.nanoTime()-start)/1000));
//    try {
//      test2();
//    } catch (BizException e) {
//      throw new BizException(e);
//    }


    test3();

  }

  private static void test(int size) {
    if (size < 0) {
      throw new IndexOutOfBoundsException(String.valueOf(size));
    }
  }

  private static void test2(){
    try {
      BufferedReader br = Files.newBufferedReader(Paths.get("ddd"));
    } catch (IOException e) {

    }
  }
  private static void test3(){
    throw new RuntimeException("이상하게 에러남");
  }
}
