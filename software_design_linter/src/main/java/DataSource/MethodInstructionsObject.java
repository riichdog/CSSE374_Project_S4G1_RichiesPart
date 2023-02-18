package DataSource;


public class MethodInstructionsObject extends InstructionObject {
    public String name;
    public String owner;
    public Boolean fromInterfacBoolean;
    public MethodInstructionsObject(String name, String owner, Boolean interfaceBool, InstructionType type){
        this.name = name;
        this.owner = owner;
        this.fromInterfacBoolean = interfaceBool;
        this.type = type;
    }
    
}
