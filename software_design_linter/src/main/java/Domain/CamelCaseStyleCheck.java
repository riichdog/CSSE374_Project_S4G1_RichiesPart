package Domain;

import java.util.ArrayList;
import java.util.HashMap;

import DataSource.*;

public class CamelCaseStyleCheck implements Rule {

    @Override
    public ArrayList<String> run(HashMap<String, ClassObject> classes) {
        // // Solution to finding camel case provided on https://stackoverflow.com/questions/23936280/matching-camelcase-to-a-regular-expression-in-java
        // String camelCasePattern = "^[a-z][a-z0-9]*(([A-Z][a-z0-9]+)*[A-Z]?|([a-z0-9]+[A-Z])*|[A-Z])$";
        // ArrayList<String> summary = new ArrayList<String>();
        // for(String c : classes.keySet()) {
        //     ClassObject current = classes.get(c);
        //     if(current.fields.isEmpty()) {
        //         summary.add(current.niceName + " has no fields.");
        //     }
        //     for(FieldObject f : current.fields) {
        //         if(f.name.matches(camelCasePattern)) {
        //             summary.add(current.niceName + "'s field " + f.name + " follows Camel Case conventions.");
        //         } else {
        //             summary.add(current.niceName + "'s field " + f.name + " does not follow Camel Case conventions.");
        //         }
        //     }
        // }
        // return summary;
        return null;
    }
}
