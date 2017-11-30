package alanbear.sample.spring.data.repository;

import static org.hibernate.jpa.QueryHints.HINT_COMMENT;

import alanbear.sample.spring.data.model.Human;
import java.util.Collection;
import java.util.List;
import javax.persistence.QueryHint;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@CacheConfig(cacheNames = "humans")
@Repository
public interface HumanRepository extends BaseRepository<Human, String> {

    //group by 要自己寫SQL

    //by id
    Human findByIdNumber(String idNumber);

    //by field
    List<Human> findBySex(String sex);

    //by page
    @QueryHints(value = { @QueryHint(name = HINT_COMMENT, value = "a query for pageable")})
    Page<Human> findBySex(String sex, Pageable pageable);

    //by order by Asc
    List<Human> findAllByOrderByWeightAsc();

    //by order by Desc
    List<Human> findAllByOrderByWeightDesc();

    //by SQL
    @Query("Select " +
            "   h " +
            "from " +
            "   Human h " +
            "where " +
            "   h.sex = :sex " +
            "and FUNCTION('YEAR', '2017-10-10') = 2017 " +
            "and FUNCTION('MONTH', '2017-10-10') = 10 ")
    List<Human> findBySexWithSQL(@Param("sex") String sex);

    //by in
    List<Human> findByFirstNameIn(Collection<String> firstName);

    //by between
    List<Human> findByHeightBetween(float down, float up);

    //by like
    @Cacheable
    List<Human> findByLastNameLike(String nameLike);
}
