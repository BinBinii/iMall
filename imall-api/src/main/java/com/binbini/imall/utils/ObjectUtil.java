package com.binbini.imall.utils;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.lang.reflect.Field;

/**
 * @Author: BinBin
 * @Date: 2022/09/15/21:42
 * @Description:
 */
@Service
@Lazy(false)
public class ObjectUtil implements Serializable {
    public static boolean checkObjAllFieldsIsNotNull(Object object) {
        if (null == object) {
            return false;
        }
        try {
            for (Field f : object.getClass().getDeclaredFields()) {
                f.setAccessible(true);
                if (f.get(object)  == null || StringUtils.isBlank(f.get(object).toString())) {
                    return false;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }
}
