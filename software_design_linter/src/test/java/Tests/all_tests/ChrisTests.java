package Tests.all_tests;

import static org.junit.Assert.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;


import Tests.test_classes.*;
import Tests.test_classes.LK_principle.DummyClass;
import Tests.test_classes.LK_principle.LeastKnowledgePass;
import Tests.test_classes.template_method.v1.*;

import org.junit.*;

import DataSource.*;
import Domain.*;


public class ChrisTests {
    public void runAllTests() {
        testIsHollywood();
        testNotHollywood();
        testIsCamelCase();
        testNotCamelCase();
    }

    @Test
    public void testIsHollywood() {
        ASMClassConverter converter = new ASMClassConverter();
        HashMap<String, ClassObject> goodboys = converter.convertAll("Tests.test_classes.hollywood_principle.GoodLowLevelClass");
        HollywoodPrincipleCheck checker = new HollywoodPrincipleCheck();
        for(String result : checker.run(goodboys)) {
            if(result.contains("GoodLowLevelClass")) {
                assertEquals(true, result.contains("follows"));
            }
        }
    }

    @Test
    public void testNotHollywood() {
        ASMClassConverter converter = new ASMClassConverter();
        HashMap<String, ClassObject> badboys = converter.convertAll("Tests.test_classes.hollywood_principle.BadLowLevelClass");
        HollywoodPrincipleCheck checker = new HollywoodPrincipleCheck();
        for(String result : checker.run(badboys)) {
            if(result.contains("BadLowLevelClass")) {
                assertEquals(true, result.contains("does not follow"));
            }
        }
    }

    @Test
    public void testIsCamelCase() {
        ASMClassConverter converter = new ASMClassConverter();
        HashMap<String, ClassObject> goodboys = converter.convertAll("Tests.test_classes.camel_case.GoodNames");
        CamelCaseStyleCheck checker = new CamelCaseStyleCheck();
        for(String result : checker.run(goodboys)) {
            assertEquals(true, result.contains("follows"));
        }
    }

    @Test
    public void testNotCamelCase() {
        ASMClassConverter converter = new ASMClassConverter();
        HashMap<String, ClassObject> badboys = converter.convertAll("Tests.test_classes.camel_case.BadNames");
        CamelCaseStyleCheck checker = new CamelCaseStyleCheck();
        for(String result : checker.run(badboys)) {
            assertEquals(true, result.contains("does not follow"));
        }
    }
}
