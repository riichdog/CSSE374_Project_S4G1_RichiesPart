package Tests.test_classes.template_method.v1;

public class BasicTemplateChild2 extends BasicTemplateAbstract {

    @Override
    public void step1() {
        this.templateAbstractField_2 =2;        
    }

    @Override
    public Boolean step2() {
        return true;
    }

    @Override
    public Integer step3() {
        // TODO Auto-generated method stub
        this.templateAbstractField_3 = 6.00;
        return (int)(this.templateAbstractField_2 +  this.templateAbstractField_3);
    }

    @Override
    public Integer step4() {
        return (int)(this.templateAbstractField_3 * 0);
    }

   
    
}
