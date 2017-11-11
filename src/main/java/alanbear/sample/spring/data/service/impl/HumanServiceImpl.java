package alanbear.sample.spring.data.service.impl;

import alanbear.sample.spring.data.model.Human;
import alanbear.sample.spring.data.repository.HumanRepository;
import alanbear.sample.spring.data.service.HumanService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service("HumanService")
public class HumanServiceImpl implements HumanService{

    @Autowired
    private HumanRepository repository;

    @Override
    public List<Human> findAll() {
        return repository.findAll();
    }
}
