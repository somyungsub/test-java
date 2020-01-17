import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class JavaVersionConfirm {
  public static void main(String[] args) throws IOException {
    Stream<Path> walk = Files.walk(Paths.get("C:\\Program Files"));

  }
}
