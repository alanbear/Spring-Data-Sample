package alanbear.sample.spring.data.repository;

import alanbear.sample.spring.data.model.Human;
import org.springframework.stereotype.Repository;

@Repository
public interface HumanRepository extends BaseRepository<Human, String> {
}
