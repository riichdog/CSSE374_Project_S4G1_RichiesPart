package Tests.test_classes.template_method.v1;

public  abstract class BasicTemplateAbstract{

    //Calcualtor Machine

    Integer templateAbstractField_1 = 1;
    Integer templateAbstractField_2,templateAbstractField_4;
    Double templateAbstractField_3;
    public BasicTemplateAbstract(){
        this.templateAbstractField_4 = 4;
    }
    public String algorithm(){
        Integer x = 5;
        this.step1();
        if(this.step2()){
            x = step3() + this.templateAbstractField_2;
        }
        x+=this.step4();
        return this.step5(x);
    }
    public abstract void step1();
    public abstract Boolean step2();
    public abstract Integer step3();
    public abstract Integer step4();
    public String step5(Integer result){
        String returnString = "YoUr AnsWer is: " + result + " thousand omg";
        return returnString;
    };


    
}