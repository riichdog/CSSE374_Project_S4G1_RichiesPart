package Tests.test_classes;

public class TestClass {
    private String testField_String;
    public Integer testField_Integer;
    protected Integer testField_Integer2 = 3;
    static Boolean testField_Boolean = false;
    public TestClassField testField_Class = new TestClassField();
    public TestClass(){
        this.testField_String = "Grass";
        this.testField_Integer = 2;
    }
    public String testMethod1(Integer gang, Double n, String em){
        this.testField_String = em;
        return this.testField_String;
    }
    protected void  testMethod2(){

    }
    private Integer testMethod3(Boolean truthy){
        this.testField_Integer = 2;
        Integer x = this.testField_Integer * this.testField_Integer2;
        return x;
    }

}
