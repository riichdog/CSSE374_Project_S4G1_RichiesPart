package Domain;

import java.util.ArrayList;
import java.util.HashMap;

import DataSource.ClassObject;
import DataSource.MethodObject;

public class UpperLowerCaseStyleCheck implements Rule {

    public HashMap<String, ClassObject> classes;

    @Override
    public ArrayList<String> run(HashMap<String, ClassObject> classes) {
        //check Class Case Tyoing && Method Case Typing
        this.classes = classes;
        ArrayList<String> result = new ArrayList<>();
        for(ClassObject clob : classes.values()){
            result.add(this.calculateResult(clob));
        }
        return result;
    }

     
    public String calculateResult(ClassObject clob) {
        if(this.passesCheck(clob)){
            String resultString = clob.niceName + " does follow the UpperCase LowerCase Naming Convention";
            return resultString;
        }
        else{
            String resultString = clob.niceName + " does not follow the UpperCase LowerCase Naming Convention";
            return resultString;
        }
    }


    public boolean passesCheck(ClassObject clob) {
        Character classFirstLetter = (cleanUp(clob.niceName)).charAt(0);
        if(!Character.isUpperCase(classFirstLetter)){ return false; }
        for(MethodObject m: clob.methods ){
            if(!cleanUp(m.name).equals("ignore")){
                Character methodFirstLetter = (cleanUp(m.name)).charAt(0);
                if(!Character.isLowerCase(methodFirstLetter)){ return false; }
            }
        }
        return true;
    }

    private String cleanUp(String name){
        if(name.contains("<")){
            return "ignore";
        }
        if(name.contains(".")){
            name = name.substring(name.lastIndexOf(".") + 1);
        }
        return name;
    }
    
}
