package alanbear.sample.test.spring.data.repository;

import alanbear.sample.spring.data.common.Sex;
import alanbear.sample.spring.data.model.Human;
import alanbear.sample.spring.data.repository.HumanRepository;
import alanbear.sample.test.spring.data.AbstractDataTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class HumanRepositoryTest extends AbstractDataTest{

    @Autowired
    private HumanRepository humanRepository;

    @Test
    public void dataSizeTest(){
        Assert.assertTrue(humanRepository.findAll().size() == 3);
    }

    @Test
    public void findByIdTest(){
        Assert.assertTrue(humanRepository.findByIdNumber("A123456789").getFirstName().equals("apple"));
    }

    @Test
    public void findBySexTest(){
        Assert.assertTrue(humanRepository.findBySex(Sex.male.getId()).size() == 1);
        Assert.assertTrue(humanRepository.findBySex(Sex.female.getId()).size() == 2);
        Assert.assertTrue(humanRepository.findBySex(Sex.other.getId()).size() == 0);
    }

    @Test
    public void findBySexWithPageTest(){

        Sort sort = new Sort(Sort.Direction.DESC, "weight");
        PageRequest pr = new PageRequest(0, 1, sort);
        Human test1 = humanRepository.findBySex(Sex.female.getId(),pr).getContent().get(0);
        Assert.assertTrue(test1.getFirstName().equals("egg"));

        Sort sort2 = new Sort(Sort.Direction.ASC, "weight");
        PageRequest pr2 = new PageRequest(0, 1, sort2);
        Human test2 = humanRepository.findBySex(Sex.female.getId(),pr2).getContent().get(0);
        Assert.assertTrue(test2.getFirstName().equals("cat"));

    }

    @Test
    public void findAllByOrderByWeightAscTest(){
        Human human = humanRepository.findAllByOrderByWeightAsc().get(0);
        Assert.assertTrue(human.getFirstName().equals("apple"));
    }

    @Test
    public void findAllByOrderByWeightDescTest(){
        Human human = humanRepository.findAllByOrderByWeightDesc().get(0);
        Assert.assertTrue(human.getFirstName().equals("egg"));
    }

    @Test
    public void findBySexWithSQLTest(){
        Assert.assertTrue(humanRepository.findBySexWithSQL(Sex.male.getId()).size() == 1);
        Assert.assertTrue(humanRepository.findBySexWithSQL(Sex.female.getId()).size() == 2);
        Assert.assertTrue(humanRepository.findBySexWithSQL(Sex.other.getId()).size() == 0);
    }

    @Test
    public void findByFirstNameInTest(){
        List<String> arr = new ArrayList<>();
        arr.add("apple");
        arr.add("cat");
        Assert.assertTrue(humanRepository.findByFirstNameIn(arr).size() == 2);
    }

    @Test
    public void findByHeightBetween(){
        List<Human> humans = humanRepository.findByHeightBetween(160,210);
        Assert.assertTrue(humans.size() == 2);
    }

    @Test
    public void findByLastNameLike(){
        List<Human> humans = humanRepository.findByLastNameLike("%nana");
        Assert.assertTrue(humans.size() == 1);
    }
}
