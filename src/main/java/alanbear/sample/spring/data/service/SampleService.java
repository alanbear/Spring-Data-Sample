package alanbear.sample.spring.data.service;

public interface SampleService {

    static void staticMethod(){
        System.out.println("This is static method");
    }

    void sampleMethod();

}
