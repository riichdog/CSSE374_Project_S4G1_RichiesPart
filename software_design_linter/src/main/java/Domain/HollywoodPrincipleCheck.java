package Domain;

import java.util.ArrayList;
import java.util.HashMap;

import DataSource.*;

public class HollywoodPrincipleCheck implements Rule {

    @Override
    public ArrayList<String> run(HashMap<String, ClassObject> classes) {
        // ArrayList<String> summary = new ArrayList<String>();
        // for(String current : classes.keySet()) {
        //     summary.add(classCheck(classes.get(current), classes));
        // }
        // return summary;
        return null;
    }

    // private String classCheck(ClassObject current, HashMap<String, ClassObject> allClasses) {
    //     String report = current.niceName;
    //     if(current.type == ClassType.INTERFACE || current.extendedClass == null) {
    //         return report + " follows the Hollywood Principle.";
    //     } else {
    //         for(MethodObject m : current.methods) {
    //             if(m.name.equals("<init>")) {
    //                 continue;
    //             }
    //             for(InstructionObject i : m.instructions) {
    //                 if(i.type == InstructionType.METHOD) {
    //                     if(((MethodInstructionsObject) i).owner == current.extendedClass) {
    //                         return report + " does not follow the Hollywood Principle.";
    //                     }
    //                 }
    //             }
    //         }
    //     }
    //     return report + " follows the Hollywood Principle.";
    // }
}
