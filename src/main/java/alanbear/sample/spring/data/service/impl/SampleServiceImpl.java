package alanbear.sample.spring.data.service.impl;

import alanbear.sample.spring.data.service.SampleService;
import org.springframework.stereotype.Service;

@Service("SampleService")
public class SampleServiceImpl implements SampleService{

    @Override
    public void sampleMethod() {
        System.out.println("This is sample method");
    }
}
