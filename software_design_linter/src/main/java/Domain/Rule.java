
package Domain;
import java.util.ArrayList;
import java.util.HashMap;

import DataSource.*;



public interface Rule {
    public ArrayList<String> run(HashMap<String,ClassObject> classes);
}
