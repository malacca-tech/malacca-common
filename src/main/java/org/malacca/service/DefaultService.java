package org.malacca.service;

import org.malacca.component.Component;
import org.malacca.definition.ComponentDefinition;
import org.malacca.messaging.Message;
import org.malacca.support.parser.Parser;

import java.util.Map;

public class DefaultService extends AbstractService {

    @Override
    public void loadComponent(ComponentDefinition definition, String type) {

    }

    @Override
    public void loadFlow(String flowStr) {

    }

    @Override
    void retryFrom(String componentId, Message message) {

    }

    private String getParserClassName(String type) {
        // TODO: 2020/2/21 type 配置文件
        return "";
    }

    // TODO: 2020/2/21
    private Component buildComponentInstance(Parser parser, ComponentDefinition componentDefinition) {
        Object instance = parser.createInstance(componentDefinition.getParams());
        // TODO: 2020/2/22 component共有参数 id name。。。
        return (Component) instance;
    }
}
