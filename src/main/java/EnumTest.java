import java.io.IOException;
import java.nio.CharBuffer;

public enum EnumTest implements Readable{
    INSTANCE;


    @Override
    public int read(CharBuffer cb) throws IOException {
        return 0;
    }
}
