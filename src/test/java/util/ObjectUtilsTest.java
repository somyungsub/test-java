package util;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static util.ObjectUtils.deepCopy;

public class ObjectUtilsTest {
    @Test
    public void deepCopyTest() {
        List<List<String>> list = new ArrayList<>();

        List<String> list2 = new ArrayList<>();
        list2.add("abc2");
        list2.add("abc2");
        list2.add("abc2");
        list2.add("abc2");
        list.add(list2);

        List<String> list3 = new ArrayList<>();
        list3.add("abc3");
        list3.add("abc3");
        list3.add("abc3");
        list3.add("abc3");
        list.add(list3);

        List<List<String>> deepCopy = deepCopy(list);
        deepCopy.get(1).set(0, "123");

        System.out.println("deepCopy 변경");
        deepCopy.forEach(System.out::println);
        System.out.println("Origin List ");
        list.forEach(System.out::println);

        System.out.println("3차 리스트 테스트");

        List<List<List<String>>> abc = new ArrayList<>();
        List<List<String>> list22 = new ArrayList<>();
        List<String> list222 = new ArrayList<>();
        list222.add("dd33");
        list222.add("dd33");
        list222.add("dd33");
        list22.add(list222);
        List<String> list223 = new ArrayList<>();
        list223.add("dd44");
        list223.add("dd44");
        list223.add("dd44");
        list22.add(list223);

        List<List<String>> list23 = new ArrayList<>();
        List<String> list333 = new ArrayList<>();
        list333.add("dd55");
        list333.add("dd55");
        list333.add("dd55");
        list23.add(list333);
        List<String> list334 = new ArrayList<>();
        list334.add("dd66");
        list334.add("dd66");
        list334.add("dd77");
        list23.add(list334);

        abc.add(list22);
        abc.add(list23);

        System.out.println("==== 변경전 ====");
        abc.forEach(System.out::println);
        List<List<List<String>>> deepCopy2 = deepCopy(abc);
        deepCopy2.get(0).get(1).set(2, "cc44");
        deepCopy2.get(1).get(0).set(1, "abc44");
        System.out.println("==== 변경후 deep ====");
        deepCopy2.forEach(System.out::println);
        System.out.println("==== 변경후 origin ====");
        abc.forEach(System.out::println);

        System.out.println("========");
//        List<List<List<String>>> deepCopy3 = new ArrayList<>();
//        Collections.copy(deepCopy3, abc);
//        deepCopy3.forEach(System.out::println);


        List<String> dd = new ArrayList<>();
        dd.add("svb");
        dd.add("svb");
        dd.add("svb");
        List<String> dd2 = new ArrayList<>();
        dd2.add("111");
        dd2.add("111");
        dd2.add("111");


        Collections.copy(dd2, dd);
        dd2.forEach(System.out::println);
    }

}
