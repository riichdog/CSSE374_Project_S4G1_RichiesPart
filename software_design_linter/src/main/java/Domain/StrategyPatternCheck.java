
package Domain;
import java.util.ArrayList;
import java.util.HashMap;

import DataSource.*;


public class StrategyPatternCheck implements Rule{
    @Override
    public ArrayList<String> run(HashMap<String, ClassObject> classes) {
        // ArrayList<String> summary = new ArrayList<String>();
        // for(String current : classes.keySet()) {
        //     summary.add(classCheck(classes.get(current), classes));
        // }
        // return summary;
        return null;
    }

    // private String classCheck(ClassObject classObject, HashMap<String, ClassObject> classes) {
    //     return null;
    // }
}
