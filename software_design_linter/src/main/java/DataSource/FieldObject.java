package DataSource;


public class FieldObject extends JavaObject {
    public String type;
    public Object value;

    public FieldObject(String name, AccessModifier accessModifier, String signature, String type, Object value) {
        this.name = name;
        this.accessModifier = accessModifier;
        this.signature = signature;
        this.type = type;
        this.value = value;
    }
}