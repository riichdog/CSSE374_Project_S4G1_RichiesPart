package DataSource;

import java.util.ArrayList;

public class ClassObject extends JavaObject {
    public String niceName;
    public ClassType type;
    public ArrayList<String> implementedClasses;
    public String extendedClass;
    public ArrayList<FieldObject> fields;
    public ArrayList<MethodObject> methods;
    public ArrayList<String> referencedClasses;

    public ClassObject(String name, AccessModifier accessModifier, String signature, String niceName, ClassType type, ArrayList<String> implementedClasses, String extendedClass, ArrayList<FieldObject> fields, ArrayList<MethodObject> methods) {
        this.name = name;
        this.accessModifier = accessModifier;
        this.signature = signature;
        this.niceName = niceName;
        this.type = type;
        this.implementedClasses = implementedClasses;
        this.extendedClass = extendedClass;
        this.fields = fields;
        this.methods = methods;
    
    }
}