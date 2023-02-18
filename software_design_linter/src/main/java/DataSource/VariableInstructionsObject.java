package DataSource;

public class VariableInstructionsObject extends InstructionObject {
    public int id;
    
    public VariableInstructionsObject(int id, InstructionType type){
        this.id = id;
        this.type = type;
    }
}
