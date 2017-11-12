package alanbear.sample.spring.data.repository;

import alanbear.sample.spring.data.model.FormInfo;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

public interface FormInfoRepository extends BaseRepository<FormInfo,String>{

    /**
     * PK不能被修改
     * 多執行緒更新編號
     * Isolation.READ_COMMITTED 避免 where 到舊號碼
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED)
    @Modifying
    @Query("update FormInfo f "
            + "set f.serialNumber = :newNum, "
            + "    f.serialNumberDate = :newDate "
            + "where 1=1 "
            + "and f.serialNumber = :num "
            + "and f.serialNumberDate = :date "
            + "and f.formId = :formId "
    )
    Integer saveWithCondition(@Param("formId") String formId,
                              @Param("newNum") Integer newNum,
                              @Param("newDate") LocalDate newDate,
                              @Param("date") LocalDate date,
                              @Param("num") Integer num);


    /**
     * 多執行緒讀取編號
     * Isolation.READ_UNCOMMITTED 避免 lock
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_UNCOMMITTED)
    FormInfo findOne(String id);
}
