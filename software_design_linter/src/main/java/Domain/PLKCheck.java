package Domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import DataSource.ClassObject;
import DataSource.FieldObject;
import DataSource.InstructionObject;
import DataSource.InstructionType;
import DataSource.MethodInstructionsObject;
import DataSource.MethodObject;

//Principle of Least Knowledge
public class PLKCheck implements Rule{
    HashMap<String, ClassObject> classes;
    @Override
    public ArrayList<String> run(HashMap<String, ClassObject> classes) {
        this.classes = classes;
        ArrayList<String> result = new ArrayList<>();
        for(ClassObject clob : classes.values()){
            result.add(this.calculateResult(clob));
        }
        return result;
    }
    
    public String calculateResult(ClassObject clob) {
        if(this.isPLK(clob)){
            String resultString = clob.niceName + " does follow the Principle of Least Knowledge";
            return resultString;
        }
        else{
            String resultString = clob.niceName + " does not follow the Principle of Least Knowledge";
            return resultString;
        }
        //
    }
    public boolean isPLK(ClassObject clob) {

        // TODO: 

            //Loop Through Class List
            //  Get a Class
            //  Get a List of all Classes it initializes (Field/Parameters/LocalVariables)
            //      Check Fields to Get Classes | add Class to classList
            //      Loop Through Methods
            //          Get Parameters of Methods | add Class to classList
            //          Get LocalVariables | add Class to classList
            
            // PROB:  Minor problem might have to figure out how to ignore stock classes agian after i stripped em WAIT EUREKA!
            //      FIX: Check if it is class list also my class list does not contain any primitives!!!!!
            //                       
            //
            //      Loop Through Methods Again
            //          Check all the MethodCallInstructions
            //          if from class list ( its is LKP ) | TRUE
            //          if not from class list (its not LKP ) | FALSE
        ArrayList<String> allowedClasses = new ArrayList<>();
        allowedClasses = getClassesFromField(clob.fields, allowedClasses);
        allowedClasses.add(cleanUp(clob.name));
        allowedClasses.add("ignore");
        for(MethodObject m: clob.methods){
            allowedClasses = getClassesFromField(m.localVariables, allowedClasses);
        }
        for(MethodObject m: clob.methods){
            for(InstructionObject i: m.instructions){
                if(i.type.equals(InstructionType.METHOD)){
                    MethodInstructionsObject iMeth = ((MethodInstructionsObject) i);
                    String name = cleanUp(iMeth.owner);
                    if(!allowedClasses.contains(name)){
                        return false;
                    }
                }
            }
        }
        return true;
    }
    
    private ArrayList<String> getClassesFromField(ArrayList<FieldObject> fields,ArrayList<String> current) {
        for(FieldObject field: fields){
            if(!current.contains(field.type)){
                current.add(field.type);
            }
        }
        return current;
    }
    private String cleanUp(String name){
        if(name.contains("java")){
            name = "ignore";
        }
        if(name.contains("/")){
            name = name.substring(name.lastIndexOf("/") + 1);
        }
        return name;
    }
    

    
}
