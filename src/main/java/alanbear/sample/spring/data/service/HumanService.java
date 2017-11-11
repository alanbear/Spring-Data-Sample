package alanbear.sample.spring.data.service;

import alanbear.sample.spring.data.model.Human;

import java.util.List;

public interface HumanService {

    List<Human> findAll();

}
