package elastic;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class LogParser {
    public static void main(String[] args) {
        Path path = Paths.get("src/main/java/elastic/cnc.log");
        System.out.println("path = " + path);
        
        final String regex = "([0-9])";

        try {
            Stream<String> lines = Files.newBufferedReader(path).lines();
            lines.forEach(s -> {
                if (s.matches(".*[()].*")) {
//                    System.out.println("s = " + s.replaceAll("(?<=\\()\\d*", "!"));
                    System.out.println("s = " + s.replaceAll("(?:\\()(\\d)", "!"));

                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
