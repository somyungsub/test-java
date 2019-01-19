import java.util.HashMap;
import java.util.Map;

public class Test {
    public static void main(String[] args) {
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

        System.out.println("aaaaa");

    }

}
