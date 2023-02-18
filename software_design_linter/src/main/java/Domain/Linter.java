package Domain;
import java.util.ArrayList;
import java.util.HashMap;

import DataSource.*;



public class Linter {
    HashMap<String, Rule> rules=  new HashMap<>();
    ClassConverter converter;
    OptionsReaderConverter options;
    HashMap<String,ClassObject> classes = new HashMap<>();

    public Linter(ClassConverter classConverter, OptionsReaderConverter optionsReader){
        this.converter = classConverter;
        this.options = optionsReader;
    }

    public Linter(ClassConverter classConverter) {
        this.converter = classConverter;
    }

    public  void addRule(String ruleName, Rule rule){
        this.rules.put(ruleName, rule);
    }

    public void removeRule(String ruleName){
        this.rules.remove(ruleName);
    }

    public HashMap<String, ArrayList<String>> execute(String classString) {
        this.classes = this.converter.convertAll(classString);
        ClassObject classOB = classes.get(classString);
        // ArrayList<String> ruleNames =  options.readOptions();
        
        HashMap<String, ArrayList<String>> linterSummary = new HashMap<String, ArrayList<String>>();
        for(String name : rules.keySet()) {
            Rule r = this.rules.get(name);
            linterSummary.put(name, r.run(this.classes));
        }
        return linterSummary;
    }
}
