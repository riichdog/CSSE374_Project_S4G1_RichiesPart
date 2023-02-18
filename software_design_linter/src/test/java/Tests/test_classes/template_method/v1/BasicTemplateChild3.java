package Tests.test_classes.template_method.v1;

public class BasicTemplateChild3 extends BasicTemplateAbstract {
    
    @Override
    public String algorithm(){
        Integer x = 5;
        this.step1();
        if(this.step2()){
            x = step3() + this.templateAbstractField_2;
        }
        return this.step5(x);
    }

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
