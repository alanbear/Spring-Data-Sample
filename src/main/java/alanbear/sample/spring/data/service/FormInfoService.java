package alanbear.sample.spring.data.service;

import alanbear.sample.spring.data.common.Form;
import alanbear.sample.spring.data.model.FormInfo;

public interface FormInfoService {

    FormInfo findByFormId(String formId);

    Integer getNewSerialNum(String formId);

    String genFormId(Form form);

}
