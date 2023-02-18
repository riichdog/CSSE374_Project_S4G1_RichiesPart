package DataSource;
import java.util.HashMap;

public interface ClassConverter {
    public HashMap<String, ClassObject> convertAll(String javaClassName);
    public ClassObject convertOneClass(String javaClassName);
}
