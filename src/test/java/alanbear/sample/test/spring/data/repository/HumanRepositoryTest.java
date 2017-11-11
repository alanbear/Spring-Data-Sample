package alanbear.sample.test.spring.data.repository;

import alanbear.sample.spring.data.repository.HumanRepository;
import alanbear.sample.test.spring.data.AbstractDataTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class HumanRepositoryTest extends AbstractDataTest{

    @Autowired
    private HumanRepository humanRepository;

    @Test
    public void defaultDataTest(){
        log.info(String.valueOf(humanRepository.findAll().size()));
        Assert.assertFalse(humanRepository.findAll().iterator().hasNext());
    }
}
