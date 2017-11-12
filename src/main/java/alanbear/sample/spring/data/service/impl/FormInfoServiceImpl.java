package alanbear.sample.spring.data.service.impl;

import alanbear.sample.spring.data.common.Form;
import alanbear.sample.spring.data.model.FormInfo;
import alanbear.sample.spring.data.repository.FormInfoRepository;
import alanbear.sample.spring.data.service.FormInfoService;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FormInfoServiceImpl implements FormInfoService {

    @Autowired
    private FormInfoRepository repository;

    @Override
    public FormInfo findByFormId(String formId) {
        return repository.findOne(formId);
    }

    /**
     * TODO 測試碰撞
     * 取表單流水號
     */
    @Override
    public Integer getNewSerialNum(String formId) {

        Integer newNum = 0;

        FormInfo info = repository.findOne(formId);

        if (info.getSerialNumberDate().equals(LocalDate.now())) {
            newNum = info.getSerialNumber() + 1;
        }

        Integer result = repository.saveWithCondition(
                formId,
                newNum,
                LocalDate.now(),
                info.getSerialNumberDate(),
                info.getSerialNumber());

        if (result == 0) {
            try {
                // Take a little break
                Thread.sleep(100);
            } catch (InterruptedException e) {
            }
            return getNewSerialNum(formId);
        }
        return newNum; // 只須回傳剛剛存成功的 num
    }

    @Override
    public String genFormId(Form form){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyMMdd");
        LocalDateTime now = LocalDateTime.now();
        Integer serialNum = getNewSerialNum(form.getId());
        return form.getId() + "-" + now.format(dtf) + "-" + String.format("%04d",serialNum);
    }

}
