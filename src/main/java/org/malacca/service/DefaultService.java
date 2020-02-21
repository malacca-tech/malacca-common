package org.malacca.service;

import org.malacca.component.Component;
import org.malacca.messaging.Message;
import org.malacca.support.parser.Parser;
import org.malacca.utils.BeanFactoryUtils;

import java.lang.reflect.Field;
import java.util.Map;

public class DefaultService extends AbstractService{

    @Override
    void loadComponent(Map<String, Object> params, String type) {

    }

    @Override
    void loadFlow(String flowStr) {

    }

    @Override
    void retryFrom(String componentId, Message message) {

    }

    private String getParserClassName(String type){
        // TODO: 2020/2/21 type 配置文件
        return "";
    }

    // TODO: 2020/2/21
    private Component buildComponentInstance(Parser parser, Map<String,Object> params) throws ClassNotFoundException {
        Object instance = parser.createInstance(params);
        return (Component) instance;
    }
}