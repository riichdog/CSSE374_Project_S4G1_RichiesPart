package Tests.test_classes.hollywood_principle;

public class GoodLowLevelClass extends SuperClass {
    @Override
    public void method1() {
        method2();
        return;
    }

    @Override
    public void method2() {
        return;
    }
}
