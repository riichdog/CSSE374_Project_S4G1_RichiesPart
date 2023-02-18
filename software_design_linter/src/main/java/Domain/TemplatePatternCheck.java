package Domain;
import java.util.ArrayList;
import java.util.HashMap;

import DataSource.*;
public class TemplatePatternCheck implements Rule{
    HashMap<String, ClassObject> classes;
    @Override
    public ArrayList<String> run(HashMap<String, ClassObject> classes) {
        this.classes = classes;
        HashMap<String, ArrayList<String>> potentialTemplates = new HashMap<>();
        ArrayList<String> nonTemplates = new ArrayList<>();
        for(ClassObject clob: classes.values()){
            if((clob.extendedClass != null) && (classes.containsKey(convertToNice(clob.extendedClass)))){
                //add abstract class and what extends it
                ClassObject ab = classes.get(convertToNice(clob.extendedClass));
                if((ab.type == ClassType.ABSTRACT)){
                    if(!potentialTemplates.containsKey(ab.niceName)){
                        ArrayList<String> kids = new ArrayList<>();
                        kids.add(clob.niceName);
                        potentialTemplates.put(ab.niceName, kids);
                    }
                    else{
                        potentialTemplates.get(ab.niceName).add(clob.niceName);
                    }
                }             
            }
            else if(!potentialTemplates.containsKey(clob.niceName)){
                nonTemplates.add(clob.niceName);
            }   
        }

        return this.calculateResult(potentialTemplates,nonTemplates);
    }

    public Integer countConcreteMethods(ClassObject clob){
        Integer count =0;
        for(MethodObject method: clob.methods){
            if(method.instructions != null){
                count++;
            }
        }
        return count;
    }
    public Integer countAbstractMethods(ClassObject clob){
        Integer count =0;
        for(MethodObject method: clob.methods){
            if(method.instructions == null || method.instructions.isEmpty()){
                count++;
            }
        }
        return count;
    }
    public String convertToNice(String className){
        return className.replace("/",".");
    }

    public ArrayList<String> calculateResult(HashMap<String, ArrayList<String>> templates, ArrayList<String> nonTemplates ){
        ArrayList<String> result = new ArrayList<>();
        for(String abstractClass: templates.keySet()){
            if(this.calculate(abstractClass, templates.get(abstractClass))){
                String r = abstractClass + " Follows the Template Method";
                result.add(r);
                for(String child: templates.get(abstractClass)){
                    String cr = child + " Follows the Template Method";
                    result.add(cr);
                }
            }
            else{
                String r = abstractClass + " Does not follow the Template Method";
                result.add(r);
                for(String child: templates.get(abstractClass)){
                    String cr = child + " Does not follow the Template Method";
                    result.add(cr);
                }
            }
            
        }
        for(String nonT: nonTemplates){
            ClassObject nonClob = this.classes.get(nonT);
            String status = nonClob.niceName + " Does not follow the Template Method";
            result.add(status);
        }
        return result;
    }

    public boolean calculate(String abstractClass, ArrayList<String> children) {
        ClassObject abstractClassObject = this.classes.get(abstractClass);
        //check if conccrete method atleast calls abstract method once
        if(!checkConcreteMethods(abstractClassObject)){ return false;}
        //PASSED
        return true;
    }
    
    public boolean checkConcreteMethods(ClassObject abstractClassObject) {
        ArrayList<String> foundMethods = new ArrayList<>();
        //find all method calls in concrete method
        ArrayList<String> abstractMethods = getAbstractMethods(abstractClassObject);
        ArrayList<String> concreteMethods =  getConcreteMethods(abstractClassObject);
        for(MethodObject method : abstractClassObject.methods){
            if(concreteMethods.contains(method.name)){
                for(InstructionObject i: method.instructions){
                    if(i.type == InstructionType.METHOD){
                        MethodInstructionsObject mi = ((MethodInstructionsObject) i);
                        foundMethods.add(mi.name);
                    }
                }
            }
        }
        for(String abstractMethodName : abstractMethods){
            if(foundMethods.contains(abstractMethodName)){
                return true;
            }
        }
        return false;
    }

    private ArrayList<String> getConcreteMethods(ClassObject abstractClassObject) {
        ArrayList<String> concreteMethods = new ArrayList<>();
        for(MethodObject method: abstractClassObject.methods){
            if(method.instructions != null ){
                concreteMethods.add(method.name);
           }
        }
        return concreteMethods;
    }

    private ArrayList<String> getAbstractMethods(ClassObject abstractClassObject) {
        ArrayList<String> abstractMethods = new ArrayList<>();
        for(MethodObject method: abstractClassObject.methods){
            if(method.instructions == null || method.instructions.isEmpty()){
                abstractMethods.add(method.name);
            }
        }
        return abstractMethods;
    }
    
}
