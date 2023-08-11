package com.hellomaker.web.model;


import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description
 * bean转换器
 * @Author xianzhikun
 * @Date 2023/3/7 14:49
 **/
public class BeanTransfer {

    /**
     * 功能描述: 转换对象
     * @Param: [source, claz]
     * @Return: T
     * @Author: xianzhikun
     * @Date: 2023/3/7 15:00
     */
    public static <T>T transfer(Object source, Class<T> claz) {
        try {
            Object toObject = claz.newInstance();
            BeanUtils.copyProperties(source, toObject);
            return (T) toObject;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 功能描述: 列表copy
     * @Param: [source, claz]
     * @Return: java.util.List<T>
     * @Author: xianzhikun
     * @Date: 2023/7/18 14:26
     */
    public static <T> List<T> transfer(List<?> source, Class<T> claz) {
        try {
            List<T> transfer = new ArrayList<>();
            for (Object sourceObject : source) {
                Object toObject = claz.newInstance();
                BeanUtils.copyProperties(sourceObject, toObject);
                transfer.add((T) toObject);
            }
            return transfer;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }


}
