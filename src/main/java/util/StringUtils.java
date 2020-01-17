package util;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class StringUtils {
    public static void jsonParse(Path path, String[] column, String seperate) {

        try (BufferedReader br = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {

            final String sep = System.lineSeparator();
            br.lines()
              .map(s -> s.split(seperate))
              .forEach(strings -> {
                StringBuffer sb = new StringBuffer();
                sb.append("{").append(sep);
                for (int i = 0; i < strings.length; i++) {
                    sb.append("\t\"").append(column[i]).append("\"").append(":\"").append(strings[i]).append("\",").append(sep);
                }
                int lastIndexOf = sb.lastIndexOf(",");
                sb.setCharAt(lastIndexOf,' ');
                sb.append("},");
                System.out.println(sb.toString());
            });

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
