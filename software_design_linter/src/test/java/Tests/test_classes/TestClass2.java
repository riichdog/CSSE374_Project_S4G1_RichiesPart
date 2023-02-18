package Tests.test_classes;

public class TestClass2 {
    protected String testField_String = "word";
    private Double testField_Double =3D;
    public Long testField_Long = 31351351501310L;
    private  Boolean testField_Boolean;
    public TestClassField testField_Class;

    public TestClass2(){
        this.testField_String = "Grass";
        this.testField_Double = 2D;
    }
    public Double testMethod1(Integer num, int num2, String word){
        return testField_Double;
    }
    static Integer  testMethod2(Boolean four){
        TestClass testClassRef = new TestClass();
        return testClassRef.testField_Integer;
    }
    
    private Integer testMethod3(Long small){
        return 3;
    }
}
