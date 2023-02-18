package Tests.test_classes.template_method.v1;

public class BasicTemplateChild1 extends BasicTemplateAbstract {

    @Override
    public void step1() {
        this.templateAbstractField_2 =2;        
    }

    @Override
    public Boolean step2() {
        return false;
    }

    @Override
    public Integer step3() {
        // TODO Auto-generated method stub
        this.templateAbstractField_3 = 4.00;
        return 18;
    }

    @Override
    public Integer step4() {
        return (int)(this.templateAbstractField_3 * 200);
    }

   
    
}
