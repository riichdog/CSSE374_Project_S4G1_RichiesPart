package DataSource;

import java.util.ArrayList;

public class MethodObject extends JavaObject{
    public String returnType;
    public ArrayList<String> exceptions;
    public ArrayList<FieldObject> params;
    public ArrayList<FieldObject> localVariables;
    public ArrayList<InstructionObject> instructions;

    public MethodObject(String name, AccessModifier accessModifier, String signature, String returnType, ArrayList<String> exceptions, ArrayList<FieldObject> params, ArrayList<FieldObject> localV, ArrayList<InstructionObject> instructions) {
        this.name = name;
        this.accessModifier = accessModifier;
        this.signature = signature;
        this.returnType = returnType;
        this.exceptions = exceptions;
        this.localVariables = localV;
        this.params = params;
        this.instructions = instructions;
    }
}