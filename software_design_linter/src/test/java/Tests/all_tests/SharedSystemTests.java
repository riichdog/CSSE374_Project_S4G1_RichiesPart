package Tests.all_tests;
import static org.junit.Assert.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;


import Tests.test_classes.*;
import org.junit.*;
import org.w3c.dom.Notation;

// import ChrisTests;
import DataSource.*;

public class SharedSystemTests {
	

	@Test
	public void testRetrievingSingleClassInformation(){
		TestClass testClass = new TestClass();
		ASMClassConverter converter = new ASMClassConverter();
		ClassObject clob =  converter.convertOneClass("Tests.test_classes.TestClass");
		//TEST FOR CLASS NAME
		String actualName = clob.niceName;
		String expectedName = "Tests.test_classes.TestClass";
		assertEquals( actualName,expectedName);		
	}

	@Test
	public void testRetrievingAllClassNodesFromOneClass() {
		TestClass2 testClass2 = new TestClass2();
		ASMClassConverter converter = new ASMClassConverter();
		 HashMap<String, ClassObject> clobs =  converter.convertAll("Tests.test_classes.TestClass2");
		List<String> allClasses = Arrays.asList("Tests.test_classes.TestClass","Tests.test_classes.TestClass2","Tests.test_classes.TestClassField");
		// System.out.println(clobs.toString());
		for(ClassObject clob: clobs.values()){
			assertTrue(allClasses.contains(clob.niceName));
		}
	}
	

	@Test
	public void testRetrievingMethodNodes() {
		TestClass2 testClass2 = new TestClass2();
		ASMClassConverter converter = new ASMClassConverter();
		ClassObject clob =  converter.convertOneClass("Tests.test_classes.TestClass2");

		//test For methods
		HashMap<String, HashMap<String,Object>> actualMethods = new HashMap<>();
		// HashMap<String, HashMap<String,Object>> actualMethods_params = new HashMap<>();
		// HashMap<String, HashMap<String,Object>> actualMethods_instructions = new HashMap<>();
		ArrayList<MethodObject> methods = clob.methods;

		//set up method1
		HashMap<String,Object> methodHash1 = new HashMap<>();
		HashMap<String,Object> methodHash2 = new HashMap<>();
		HashMap<String,Object> methodHash3 = new HashMap<>();

		//return type and access
		methodHash1.put("Double", AccessModifier.PUBLIC);
		methodHash2.put("Integer", AccessModifier.STATIC);
		methodHash3.put("Integer", AccessModifier.PRIVATE);
		//parameters
		methodHash1.put("num","Integer");
		methodHash1.put("num2","int");
		methodHash1.put("word","String");
		methodHash2.put("four","Boolean");
		methodHash3.put("small","Long");
		//intrsuctions

		actualMethods.put("testMethod1",methodHash1);
		actualMethods.put("testMethod2",methodHash2);
		actualMethods.put("testMethod3",methodHash3);
		actualMethods.put("<clinit>",null);
		actualMethods.put("<init>",null);
		
		for(MethodObject method: methods){
			String methodName = method.name;
			if( !methodName.equals("<init>") && !methodName.equals("<clinit>")){

				AccessModifier methodAccess = method.accessModifier;
				String methodReturn = method.returnType;
				//check method name
				assertTrue(actualMethods.containsKey(methodName));
				HashMap<String,Object> methodHash = actualMethods.get(methodName);
				//check return type
				assertTrue(methodHash.containsKey(methodReturn));
				// check method access
				assertEquals(methodHash.get(methodReturn), methodAccess);
				for(FieldObject param:  method.params){
					String paramName = param.name;
					String paramType = param.type;
					//check param name
					assertTrue(methodHash.containsKey(paramName));
					//check param type
					assertEquals(methodHash.get(paramName), paramType);
				}
				//how to test instrucitons
				// for(InstructionObject ins: method.instructions){
				// 	String = 
				// }
	

			}
		}

		
	}

	@Test
	public void testRetrievingMethodNodes2() {
		TestClass testClass = new TestClass();
		ASMClassConverter converter = new ASMClassConverter();
		ClassObject clob =  converter.convertOneClass("Tests.test_classes.TestClass");
		//test For methods
		HashMap<String, HashMap<String,Object>> actualMethods = new HashMap<>();
		// HashMap<String, HashMap<String,Object>> actualMethods_params = new HashMap<>();
		// HashMap<String, HashMap<String,Object>> actualMethods_instructions = new HashMap<>();
		ArrayList<MethodObject> methods = clob.methods;

		//set up method1
		HashMap<String,Object> methodHash1 = new HashMap<>();
		HashMap<String,Object> methodHash2 = new HashMap<>();
		HashMap<String,Object> methodHash3 = new HashMap<>();

		//return type and access
		methodHash1.put("String", AccessModifier.PUBLIC);
		methodHash2.put("V", AccessModifier.PROTECTED);
		methodHash3.put("Integer", AccessModifier.PRIVATE);
		//parameters
		methodHash1.put("gang","Integer");
		methodHash1.put("n","Double");
		methodHash1.put("em","String");
		methodHash3.put("truthy","Boolean");
		//intrsuctions

		actualMethods.put("testMethod1",methodHash1);
		actualMethods.put("testMethod2",methodHash2);
		actualMethods.put("testMethod3",methodHash3);
		actualMethods.put("<clinit>",null);
		actualMethods.put("<init>",null);
		for(MethodObject method: methods){
				String methodName = method.name;
				if( !methodName.equals("<init>") && !methodName.equals("<clinit>")){

					AccessModifier methodAccess = method.accessModifier;
					String methodReturn = method.returnType;
					//check method name
					assertTrue(actualMethods.containsKey(methodName));
					HashMap<String,Object> methodHash = actualMethods.get(methodName);
					//check return type
					assertTrue(methodHash.containsKey(methodReturn));
					// check method access
					assertEquals(methodHash.get(methodReturn), methodAccess);
					for(FieldObject param:  method.params){
						String paramName = param.name;
						String paramType = param.type;
						//check param name
						assertTrue(methodHash.containsKey(paramName));
						//check param type
						assertEquals(methodHash.get(paramName), paramType);
					}
				}
				
			}
	}

	@Test
	public void testRetrievingFieldNodes() {
		TestClass2 testClass2 = new TestClass2();
		ASMClassConverter converter = new ASMClassConverter();
		ClassObject clob =  converter.convertOneClass("Tests.test_classes.TestClass2");
		//TEST FOR CLASS FIELDS
		HashMap<String, List<Object>> actualFields = new HashMap<>();
		actualFields.put("testField_String",Arrays.asList("String", AccessModifier.PROTECTED));
		actualFields.put("testField_Double",Arrays.asList("Double", AccessModifier.PRIVATE));
		actualFields.put("testField_Long",Arrays.asList("Long", AccessModifier.PUBLIC));
		actualFields.put("testField_Boolean",Arrays.asList("Boolean", AccessModifier.PRIVATE));
		actualFields.put("testField_Class",Arrays.asList("TestClassField", AccessModifier.PUBLIC));

		ArrayList<FieldObject> fields = clob.fields;
		for(FieldObject f: fields){
			String expectedFieldName = f.name;
			String expectedFieldType = f.type;
			AccessModifier expectedFieldAccess = f.accessModifier;
			assertTrue(actualFields.containsKey(expectedFieldName));
			assertTrue(actualFields.get(expectedFieldName).contains(expectedFieldType));
			assertTrue(actualFields.get(expectedFieldName).contains(expectedFieldAccess));
			
		}

	}

	@Test
	public void testRetrievingFieldNodes2(){
		TestClass testClass = new TestClass();
		ASMClassConverter converter = new ASMClassConverter();
		ClassObject clob =  converter.convertOneClass("Tests.test_classes.TestClass");
		//TEST FOR CLASS FIELDS
		HashMap<String, List<Object>> actualFields = new HashMap<>();
		actualFields.put("testField_String",Arrays.asList("String", AccessModifier.PRIVATE));
		actualFields.put("testField_Integer",Arrays.asList("Integer", AccessModifier.PUBLIC));
		actualFields.put("testField_Integer2",Arrays.asList("Integer", AccessModifier.PROTECTED));
		actualFields.put("testField_Boolean",Arrays.asList("Boolean", AccessModifier.STATIC));
		actualFields.put("testField_Class",Arrays.asList("TestClassField", AccessModifier.PUBLIC));

		ArrayList<FieldObject> fields = clob.fields;
		for(FieldObject f: fields){
			String expectedFieldName = f.name;
			String expectedFieldType = f.type;
			AccessModifier expectedFieldAccess = f.accessModifier;
			assertTrue(actualFields.containsKey(expectedFieldName));
			assertTrue(actualFields.get(expectedFieldName).contains(expectedFieldType));
			assertTrue(actualFields.get(expectedFieldName).contains(expectedFieldAccess));
			assertTrue(f.accessModifier != AccessModifier.DEFAULT);
		}

	}

	@Test
	public void testRetrievingMethodInstruction() {
		TestClass testClass = new TestClass();
		ASMClassConverter converter = new ASMClassConverter();
		ClassObject clob =  converter.convertOneClass("Tests.test_classes.TestClass");
		Integer count = 0;
		Integer expectedCount = 0;
		HashMap<String, Integer> methodCounts = new HashMap<>();
		methodCounts.put("testMethod1", 0);
		methodCounts.put("testMethod2", 0);
		methodCounts.put("testMethod3", 4);
		// Integer methodIndex = 0;
		for(MethodObject m : clob.methods) {
			if(m.name.equals("<clinit>") || m.name.equals("<init>")) {
				continue;
			}
			count = 0;
			expectedCount = methodCounts.get(m.name);
			for(InstructionObject i : m.instructions) {
				if(i.type == InstructionType.METHOD) {
					count++;
				}
			}
			assertEquals(expectedCount, count);
		}
	}
	@Test
	public void testMethodLocalVariables(){
		TestClass testClass = new TestClass();
		ASMClassConverter converter = new ASMClassConverter();
		ClassObject clob =  converter.convertOneClass("Tests.test_classes.TestClassField");
		HashMap<String, List<String>> acceptedParameters = new HashMap<>();
		acceptedParameters.put("testFieldMethod",Arrays.asList(""));
		acceptedParameters.put("testFieldMethod2",Arrays.asList("yes","no"));
		acceptedParameters.put("testFieldMethod3",Arrays.asList("google","yahoo"));
		String notAccepted = "maybe";
		String notAccepted2 = "bing";
		
		for(MethodObject m: clob.methods){
			for(FieldObject lv: m.localVariables){
				List<String> expectedResults = acceptedParameters.get(m.name);
				assertTrue(expectedResults.contains(lv.name));
				assertTrue(!notAccepted.equalsIgnoreCase(lv.name));
				assertTrue(!notAccepted2.equalsIgnoreCase(lv.name));
			}
			
		}

	}
	
	
	@Test
	public void testRetrievingVariableInstruction() {
		TestClass testClass = new TestClass();
		ASMClassConverter converter = new ASMClassConverter();
		ClassObject clob =  converter.convertOneClass("Tests.test_classes.TestClass");
		Integer count = 0;
		Integer expectedCount = 0;
		HashMap<String, Integer> methodCounts = new HashMap<>();
		methodCounts.put("testMethod1", 3);
		methodCounts.put("testMethod2", 0);
		methodCounts.put("testMethod3", 5);
		// Integer methodIndex = 0;
		for(MethodObject m : clob.methods) {
			if(m.name.equals("<clinit>") || m.name.equals("<init>")) {
				continue;
			}
			count = 0;
			expectedCount = methodCounts.get(m.name);
			for(InstructionObject i : m.instructions) {
				if(i.type == InstructionType.VARIABLE) {
					count++;
				}
			// System.out.println(m.name + " " + count);
			}
			assertEquals(expectedCount, count);
		}
	}

	@Test
	public void testRetrievingFieldInstruction() {
		TestClass testClass = new TestClass();
		ASMClassConverter converter = new ASMClassConverter();
		ClassObject clob =  converter.convertOneClass("Tests.test_classes.TestClass");
		Integer count = 0;
		Integer expectedCount = 0;
		HashMap<String, Integer> methodCounts = new HashMap<>();
		methodCounts.put("testMethod1", 2);
		methodCounts.put("testMethod2", 0);
		methodCounts.put("testMethod3", 3);
		// Integer methodIndex = 0;
		for(MethodObject m : clob.methods) {
			if(m.name.equals("<clinit>") || m.name.equals("<init>")) {
				continue;
			}
			count = 0;
			expectedCount = methodCounts.get(m.name);
			for(InstructionObject i : m.instructions) {
				if(i.type == InstructionType.FIELD) {
					count++;
				}
			}
			assertEquals(expectedCount, count);
		}
	}

	
	public void rullAllTests(){
		RichieTests r_tester = new RichieTests();
		// ChrisTests c_tester = new ChrisTests();
		this.runAllSharedSystemTests();
		// r_tester.runAllTests();
		// c_tester.runAllTests();
	}
	public void runAllSharedSystemTests() {
		this.testRetrievingAllClassNodesFromOneClass();
		this.testRetrievingSingleClassInformation();
		this.testRetrievingFieldNodes();
		this.testRetrievingFieldNodes2();
		this.testRetrievingMethodNodes();
		this.testRetrievingMethodNodes2();
		this.testRetrievingVariableInstruction();
		this.testRetrievingMethodInstruction();
		// this.testVariableInstructionType();
		this.testMethodLocalVariables();
		this.testRetrievingFieldInstruction();
	}
	
	public static void main(String[] args) {
		SharedSystemTests tester = new SharedSystemTests();
		tester.runAllSharedSystemTests();
	}

	

}
