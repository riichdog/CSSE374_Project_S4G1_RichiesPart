package DataSource;

public class FieldInstructionsObject extends InstructionObject {
    public String name;
    public String owner;
    public FieldInstructionsObject(String name, String owner, InstructionType type){
        this.name = name;
        this.owner = owner;
        this.type = type;
    }

}
