package com.example.spring_school.service.impl;

import com.example.spring_school.constant.ErrorCode;
import com.example.spring_school.exception.BusinessException;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;

public abstract class A_Service {
    void validateText(List<String> texts) {
        texts.stream().forEach(item -> validateText(item));
    }

    void validateText(String ...texts) {
        Arrays.asList(texts).stream().forEach(item -> validateText(item));
    }

    void validateText(String text) {
        validateText(text, true,null ,null );
    }

    void validateText(String text, boolean mandatory, Integer minLg, Integer maxLg) {
        if (mandatory && StringUtils.isEmpty(text)) {
            throw new BusinessException(ErrorCode.PARAM_INVALID.code, "param invalid");
        }
        // todo validate minLg, maxLg
    }


}
