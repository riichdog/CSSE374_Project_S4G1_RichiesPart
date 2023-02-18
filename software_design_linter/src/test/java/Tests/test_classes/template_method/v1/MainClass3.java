package Tests.test_classes.template_method.v1;

import java.util.ArrayList;

public class MainClass3 {
    public static void main(String[] args) {
        BasicTemplateAbstract t1  = new BasicTemplateChild1();
        BasicTemplateAbstract t2  = new BasicTemplateChild2();
        ArrayList<BasicTemplateAbstract> templates = new ArrayList<>();
        templates.add(t2);
        templates.add(t1);
        TemplateMethodFail t = new TemplateFailChild1();
        NonAbstractAbstract naa = new NonAbstractChild();
        naa.XXX();
        Integer x = t.run();
        BasicInterface bI = new BasicInterfaceImp();
        for(BasicTemplateAbstract bta: templates){
            System.out.println(bta.algorithm());
        }
    }
    
}

