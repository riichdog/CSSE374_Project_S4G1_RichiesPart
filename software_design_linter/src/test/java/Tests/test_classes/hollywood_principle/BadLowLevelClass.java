package Tests.test_classes.hollywood_principle;

public class BadLowLevelClass extends SuperClass {
    @Override
    public void method1() {
        super.bigmethod();
        return;
    }

    @Override
    public void method2() {
        return;
    }
}
