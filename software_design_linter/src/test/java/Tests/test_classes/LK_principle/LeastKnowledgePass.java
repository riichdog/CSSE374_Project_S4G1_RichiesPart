package Tests.test_classes.LK_principle;

import java.lang.reflect.Field;

public class LeastKnowledgePass {

    public DummyClass dummyField = new DummyClass();
    public void method1(Integer x){
        x = x+3;
        this.dummyField.method1();
    }
    
}
