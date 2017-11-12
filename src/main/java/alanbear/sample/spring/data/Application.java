package alanbear.sample.spring.data;

import alanbear.sample.spring.data.service.SampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;

@EnableCaching
@SpringBootApplication
public class Application {

    @Autowired
    private SampleService sampleService;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);

        //interface static
        SampleService.staticMethod();
    }

    @Bean
    public Object sampleBean(){
        sampleService.sampleMethod();
        return null;
    }

}
