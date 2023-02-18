package Presentation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import DataSource.*;
import Domain.*;


public class LinterUI {
    public static void main(String[] args) {
        ASMClassConverter converter = new ASMClassConverter();
        Linter linter = new Linter(converter, new OptionsFileConverter());
        Scanner in = new Scanner(System.in);
        String classString = "";
        System.out.println("\nWelcome to our Java Design Linter program.\n\n" +
        "Please enter the name of the Java class you would like a design analysis performed on:");
        classString = in.nextLine();
        // TODO: Add all Rules
        linter.addRule("Strategy Pattern Check", new StrategyPatternCheck());
        linter.addRule("Template Pattern Check", new TemplatePatternCheck());
        linter.addRule("Camel Case Style Check", new CamelCaseStyleCheck());
        linter.addRule("Hollywood Principle Check", new HollywoodPrincipleCheck());
        HashMap<String, ArrayList<String>> linterSummary = linter.execute(classString);
        printResults(linterSummary);
        in.close();
    }

    private static void printResults(HashMap<String, ArrayList<String>> results) {
        System.out.println("\n----------------------------------------------------------------\n");
        for(String ruleName : results.keySet()) {
            System.out.println("Results for " + ruleName + ":");
            for(String result : results.get(ruleName)) {
                System.out.println(result);
            }
            System.out.println();
        }
    }
}
