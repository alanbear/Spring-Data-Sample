package alanbear.sample.test.spring.data.datasource;

import alanbear.sample.test.spring.data.AbstractDataTest;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Map;

public class DataSourceTest extends AbstractDataTest{

    @Autowired
    @Qualifier("sampleJdbcTemplate")
    private JdbcTemplate JdbcTemplate;

    @Test
    public void amsDataSourceTest(){
        List<Map<String, Object>> rs = JdbcTemplate.queryForList("select 1");
        Assert.assertTrue(rs.get(0).values().toArray()[0].toString().equals("1"));
    }

}
