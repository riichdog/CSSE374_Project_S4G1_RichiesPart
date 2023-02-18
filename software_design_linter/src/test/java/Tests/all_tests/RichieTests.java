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

public class RichieTests {
	@Test
	public void testGettingExtended(){
		ClassConverter converter = new ASMClassConverter();
		MainClass mainClass1 = new MainClass();
		HashMap<String, ClassObject> clobs = converter.convertAll("Tests.test_classes.template_method.v1.BasicTemplateChild3");
		List<String> expectedClassNames = Arrays.asList("Tests.test_classes.template_method.v1.BasicTemplateChild3","Tests.test_classes.template_method.v1.BasicTemplateAbstract");
		for(String className: expectedClassNames){
			assertTrue(clobs.containsKey(className));
		}
	}

	@Test
	public void testTemplateMethod() {
		ClassConverter converter = new ASMClassConverter();
		Linter linter = new Linter(converter, null);
		MainClass mainClass1 = new MainClass();
		linter.addRule("TemplatePatternCheck", new TemplatePatternCheck());
		HashMap<String, ArrayList<String>> response = linter.execute("Tests.test_classes.template_method.v1.MainClass");
		ArrayList<String> expectedResponses = new ArrayList<>();
		expectedResponses.add("Tests.test_classes.template_method.v1.BasicTemplateAbstract Follows the Template Method");
		expectedResponses.add("Tests.test_classes.template_method.v1.BasicTemplateChild1 Follows the Template Method");
		expectedResponses.add("Tests.test_classes.template_method.v1.BasicTemplateChild2 Follows the Template Method");
		expectedResponses.add("Tests.test_classes.template_method.v1.MainClass Does not follow the Template Method");
		expectedResponses.add("Tests.test_classes.template_method.v1.BasicInterface Does not follow the Template Method");
		expectedResponses.add("Tests.test_classes.template_method.v1.BasicInterfaceImp Does not follow the Template Method");
		for(String check: expectedResponses){
			assertTrue(response.get("TemplatePatternCheck").contains(check));
		}

	}
	@Test
	public void testTemplateMethod2() {
		ClassConverter converter = new ASMClassConverter();
		Linter linter = new Linter(converter, null);
		MainClass mainClass1 = new MainClass();
		linter.addRule("TemplatePatternCheck", new TemplatePatternCheck());
		HashMap<String, ArrayList<String>> response = linter.execute("Tests.test_classes.template_method.v1.BasicTemplateChild3");
		ArrayList<String> expectedResponses = new ArrayList<>();
		expectedResponses.add("Tests.test_classes.template_method.v1.BasicTemplateAbstract Follows the Template Method");
		expectedResponses.add("Tests.test_classes.template_method.v1.BasicTemplateChild3 Follows the Template Method");
		for(String check: expectedResponses){
			assertTrue(response.get("TemplatePatternCheck").contains(check));
		}

	}
	@Test
	public void testTemplateMethod3() {
		ClassConverter converter = new ASMClassConverter();
		Linter linter = new Linter(converter, null);
		TestClass testClass = new TestClass();
		linter.addRule("TemplatePatternCheck", new TemplatePatternCheck());
		HashMap<String, ArrayList<String>> response = linter.execute("Tests.test_classes.TestClass2");
		ArrayList<String> expectedResponses = new ArrayList<>();
		expectedResponses.add("Tests.test_classes.TestClassField Does not follow the Template Method");
		expectedResponses.add("Tests.test_classes.TestClass Does not follow the Template Method");
		expectedResponses.add("Tests.test_classes.TestClass2 Does not follow the Template Method");
		for(String check: expectedResponses){
			assertTrue(response.get("TemplatePatternCheck").contains(check));
		}
	}
	@Test
	public void testTemplateMethod4() {
		ClassConverter converter = new ASMClassConverter();
		Linter linter = new Linter(converter);
		MainClass mainClass1 = new MainClass();
		linter.addRule("TemplatePatternCheck", new TemplatePatternCheck());
		HashMap<String, ArrayList<String>> response = linter.execute("Tests.test_classes.template_method.v1.MainClass2");
		ArrayList<String> expectedResponses = new ArrayList<>();
		expectedResponses.add("Tests.test_classes.template_method.v1.BasicTemplateAbstract Follows the Template Method");
		expectedResponses.add("Tests.test_classes.template_method.v1.BasicTemplateChild1 Follows the Template Method");
		expectedResponses.add("Tests.test_classes.template_method.v1.BasicTemplateChild2 Follows the Template Method");
		expectedResponses.add("Tests.test_classes.template_method.v1.MainClass2 Does not follow the Template Method");
		expectedResponses.add("Tests.test_classes.template_method.v1.BasicInterface Does not follow the Template Method");
		expectedResponses.add("Tests.test_classes.template_method.v1.BasicInterfaceImp Does not follow the Template Method");
		expectedResponses.add("Tests.test_classes.template_method.v1.TemplateMethodFail Does not follow the Template Method");
		expectedResponses.add("Tests.test_classes.template_method.v1.TemplateFailChild1 Does not follow the Template Method");
		for(String check: expectedResponses){
			assertTrue(response.get("TemplatePatternCheck").contains(check));
		}
		
	}
	@Test
	public void testTemplateMethod5() {
		ClassConverter converter = new ASMClassConverter();
		Linter linter = new Linter(converter);
		MainClass mainClass1 = new MainClass();
		linter.addRule("TemplatePatternCheck", new TemplatePatternCheck());
		HashMap<String, ArrayList<String>> response = linter.execute("Tests.test_classes.template_method.v1.MainClass3");
		ArrayList<String> expectedResponses = new ArrayList<>();
		expectedResponses.add("Tests.test_classes.template_method.v1.BasicTemplateAbstract Follows the Template Method");
		expectedResponses.add("Tests.test_classes.template_method.v1.BasicTemplateChild1 Follows the Template Method");
		expectedResponses.add("Tests.test_classes.template_method.v1.BasicTemplateChild2 Follows the Template Method");
		expectedResponses.add("Tests.test_classes.template_method.v1.MainClass3 Does not follow the Template Method");
		expectedResponses.add("Tests.test_classes.template_method.v1.BasicInterface Does not follow the Template Method");
		expectedResponses.add("Tests.test_classes.template_method.v1.BasicInterfaceImp Does not follow the Template Method");
		expectedResponses.add("Tests.test_classes.template_method.v1.TemplateMethodFail Does not follow the Template Method");
		expectedResponses.add("Tests.test_classes.template_method.v1.TemplateFailChild1 Does not follow the Template Method");
		expectedResponses.add("Tests.test_classes.template_method.v1.NonAbstractAbstract Does not follow the Template Method");
		expectedResponses.add("Tests.test_classes.template_method.v1.NonAbstractChild Does not follow the Template Method");
		
		for(String check: expectedResponses){
			assertTrue(response.get("TemplatePatternCheck").contains(check));
		}
		
	}
	@Test
	public void testConcreteCount() {
		ClassConverter converter = new ASMClassConverter();
		TestClass testClass = new TestClass();
		TemplatePatternCheck templatePatterCheck =  new TemplatePatternCheck();
		ClassObject clob = converter.convertOneClass("Tests.test_classes.TestClass2");
		Integer actual = templatePatterCheck.countConcreteMethods(clob);
		Integer expected =4;
		assertEquals(expected, actual);
	}
	@Test
	public void testAbstractCount() {
		ClassConverter converter = new ASMClassConverter();
		TestClass testClass = new TestClass();
		TemplatePatternCheck templatePatterCheck =  new TemplatePatternCheck();
		ClassObject clob = converter.convertOneClass("Tests.test_classes.TestClass2");
		Integer actual = templatePatterCheck.countAbstractMethods(clob);
		Integer expected =0;
		assertEquals(expected, actual);
	}
	@Test
	public void testAbstractCount2() {
		ClassConverter converter = new ASMClassConverter();
		BasicTemplateAbstract testClass = new BasicTemplateChild1();
		TemplatePatternCheck templatePatterCheck =  new TemplatePatternCheck();
		ClassObject clob = converter.convertOneClass("Tests.test_classes.template_method.v1.BasicTemplateAbstract");
		Integer actual = templatePatterCheck.countAbstractMethods(clob);
		Integer expected =4;
		assertEquals(expected, actual);
	}
	@Test
	public void testClassTypes(){
		ClassConverter converter = new ASMClassConverter();
		MainClass mainClass1 = new MainClass();
		HashMap<String, ClassObject> clobs = converter.convertAll("Tests.test_classes.template_method.v1.MainClass");
		HashMap<String,ClassType> expectedTypes = new HashMap<>();
		expectedTypes.put("Tests.test_classes.template_method.v1.MainClass",ClassType.CLASS);
		expectedTypes.put("Tests.test_classes.template_method.v1.BasicTemplateAbstract",ClassType.ABSTRACT);
		expectedTypes.put("Tests.test_classes.template_method.v1.BasicInterface",ClassType.INTERFACE);
		expectedTypes.put("Tests.test_classes.template_method.v1.BasicTemplateChild1",ClassType.CLASS);
		expectedTypes.put("Tests.test_classes.template_method.v1.BasicTemplateChild2",ClassType.CLASS);
		expectedTypes.put("Tests.test_classes.template_method.v1.BasicInterfaceImp",ClassType.CLASS);
		for(String className: clobs.keySet()){
			ClassObject clob = clobs.get(className);
			ClassType expectedType = expectedTypes.get(className);
			ClassType returnedType = clob.type;
			assertEquals(expectedType, returnedType);
		}


	}
	@Test
	public void testClassIsAbstract(){
		ClassConverter converter = new ASMClassConverter();
		MainClass mainClass1 = new MainClass();
		ClassObject clob = converter.convertOneClass("Tests.test_classes.template_method.v1.BasicTemplateAbstract");
		assertEquals(ClassType.ABSTRACT,clob.type);
	}
	@Test
	public void testClassIsNotAbstract(){
		TestClass testClass = new TestClass();
		ClassConverter converter = new ASMClassConverter();
		ClassObject clob =  converter.convertOneClass("Tests.test_classes.TestClass");
		assertEquals(ClassType.CLASS,clob.type);
	}

	@Test
	public void testPrincipleOfLeastKnowledge(){
		ClassConverter converter = new ASMClassConverter();
		LeastKnowledgePass testClass = new LeastKnowledgePass();
		HashMap<String, ClassObject> clobs = converter.convertAll("Tests.test_classes.LK_principle.LeastKnowledgePass");
		PLKCheck principle_least =  new PLKCheck();
		ArrayList<String> result = principle_least.run(clobs);
		Boolean actual = principle_least.isPLK(clobs.get("Tests.test_classes.LK_principle.LeastKnowledgePass"));
		Boolean expected =true;
		assertTrue(actual);
	}
	@Test
	public void testPrincipleOfLeastKnowledge2(){
		ClassConverter converter = new ASMClassConverter();
		LeastKnowledgePass testClass = new LeastKnowledgePass();
		HashMap<String, ClassObject> clobs = converter.convertAll("Tests.test_classes.LK_principle.LeastKnowledgeFail");
		PLKCheck principle_least =  new PLKCheck();
		ArrayList<String> result = principle_least.run(clobs);
		Boolean actual = principle_least.isPLK(clobs.get("Tests.test_classes.LK_principle.LeastKnowledgeFail"));
		Boolean expected =false;
		assertFalse(actual);
	}
	 @Test
	public void testPrincipleOfLeastKnowledge3(){
		ClassConverter converter = new ASMClassConverter();
		Linter linter = new Linter(converter);
		MainClass mainClass1 = new MainClass();
		linter.addRule("PLKCheck", new PLKCheck());
		
		HashMap<String, ArrayList<String>> response = linter.execute("Tests.test_classes.LK_principle.LeastKnowledgePass");
		ArrayList<String> expectedResponses = new ArrayList<>();
		expectedResponses.add("Tests.test_classes.LK_principle.LeastKnowledgePass does follow the Principle of Least Knowledge");
		expectedResponses.add("Tests.test_classes.LK_principle.DummyClass2 does follow the Principle of Least Knowledge");
		expectedResponses.add("Tests.test_classes.LK_principle.DummyClass does not follow the Principle of Least Knowledge");

		for(String check: expectedResponses){
			assertTrue(response.get("PLKCheck").contains(check));
		}
	 }
	 @Test
	 public void testRemoveRule(){
		ClassConverter converter = new ASMClassConverter();
		Linter linter = new Linter(converter);
		MainClass mainClass1 = new MainClass();
		linter.addRule("PLKCheck", new PLKCheck());
		linter.addRule("Template", new PLKCheck());
		linter.removeRule("Template");
		HashMap<String, ArrayList<String>> response = linter.execute("Tests.test_classes.LK_principle.LeastKnowledgePass");
		ArrayList<String> expectedResponses = new ArrayList<>();
		expectedResponses.add("Tests.test_classes.LK_principle.LeastKnowledgePass does follow the Principle of Least Knowledge");
		expectedResponses.add("Tests.test_classes.LK_principle.DummyClass2 does follow the Principle of Least Knowledge");
		expectedResponses.add("Tests.test_classes.LK_principle.DummyClass does not follow the Principle of Least Knowledge");

		for(String key: response.keySet()){
			for(String check: response.get(key)){
				assertTrue(expectedResponses.contains(check));
			}
		}
	 }

	@Test
	public void testMethodNameIsLowerCase(){
		ClassConverter converter = new ASMClassConverter();
		LeastKnowledgePass testClass = new LeastKnowledgePass();
		HashMap<String, ClassObject> clobs = converter.convertAll("Tests.test_classes.cases_styleCheck.CorrectNamingConvention");
		UpperLowerCaseStyleCheck principle_least =  new UpperLowerCaseStyleCheck();
		ArrayList<String> result = principle_least.run(clobs);
		Boolean actual = principle_least.passesCheck(clobs.get("Tests.test_classes.cases_styleCheck.CorrectNamingConvention"));
		Boolean expected =true;
		assertTrue(actual);
	}
	@Test
	public void testMethodNameIsLowerCase2(){
		ClassConverter converter = new ASMClassConverter();
		LeastKnowledgePass testClass = new LeastKnowledgePass();
		HashMap<String, ClassObject> clobs = converter.convertAll("Tests.test_classes.cases_styleCheck.Incorrectnamingconvention");
		UpperLowerCaseStyleCheck principle_least =  new UpperLowerCaseStyleCheck();
		ArrayList<String> result = principle_least.run(clobs);
		Boolean actual = principle_least.passesCheck(clobs.get("Tests.test_classes.cases_styleCheck.Incorrectnamingconvention"));
		Boolean expected =false;
		assertFalse(actual);
	}
	@Test
	public void testMethodNameIsLowerCase4(){
		ClassConverter converter = new ASMClassConverter();
		LeastKnowledgePass testClass = new LeastKnowledgePass();
		HashMap<String, ClassObject> clobs = converter.convertAll("Tests.test_classes.cases_styleCheck.incorrectnamingconvention2");
		UpperLowerCaseStyleCheck principle_least =  new UpperLowerCaseStyleCheck();
		ArrayList<String> result = principle_least.run(clobs);
		Boolean actual = principle_least.passesCheck(clobs.get("Tests.test_classes.cases_styleCheck.incorrectnamingconvention2"));
		Boolean expected =false;
		assertFalse(actual);
	}
	@Test
	public void testMethodNameIsLowerCase3(){
		ClassConverter converter = new ASMClassConverter();
		Linter linter = new Linter(converter);
		MainClass mainClass1 = new MainClass();
		linter.addRule("UpperLower", new UpperLowerCaseStyleCheck());
		HashMap<String, ArrayList<String>> response = linter.execute("Tests.test_classes.cases_styleCheck.CorrectNamingConvention");
		ArrayList<String> expectedResponses = new ArrayList<>();
		expectedResponses.add("Tests.test_classes.cases_styleCheck.Incorrectnamingconvention does not follow the UpperCase LowerCase Naming Convention");
		expectedResponses.add("Tests.test_classes.cases_styleCheck.CorrectNamingConvention does follow the UpperCase LowerCase Naming Convention");

		for(String check: expectedResponses){
			assertTrue(response.get("UpperLower").contains(check));
		}
		// for(String key: response.keySet()){
		// 	for(String r: response.get(key)){
		// 		System.out.println(r);
		// 	}
		// }
		
	}

	public void runAllTests() {
		this.testTemplateMethod();
		this.testGettingExtended();
		this.testClassTypes();
		this.testClassIsNotAbstract();
		this.testClassIsAbstract();
		this.testTemplateMethod2();
		this.testTemplateMethod3();
		this.testAbstractCount();
		this.testAbstractCount2();
		this.testConcreteCount();
		this.testPrincipleOfLeastKnowledge();
		this.testPrincipleOfLeastKnowledge2();
		this.testPrincipleOfLeastKnowledge3();
		this.testTemplateMethod4();
		this.testRemoveRule();
		this.testMethodNameIsLowerCase();
		this.testMethodNameIsLowerCase2();
		this.testMethodNameIsLowerCase3();
		this.testMethodNameIsLowerCase4();
		this.testTemplateMethod5();
	}
	public static void main(String[] args) {
		RichieTests rT = new RichieTests();
		rT.runAllTests();
	}
}
