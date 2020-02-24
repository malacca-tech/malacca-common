package org.malacca.service;

import org.malacca.component.Component;
import org.malacca.definition.ComponentDefinition;
import org.malacca.definition.EntryDefinition;
import org.malacca.entry.Entry;
import org.malacca.messaging.Message;
import org.malacca.support.parser.Parser;

public class DefaultService extends AbstractService {

    @Override
    public void loadFlow(String flowStr) {

    }

    @Override
    Parser getParserByType(String type) {
        // TODO: 2020/2/24 获取解析器
        return null;
    }

    @Override
    void retryFrom(String componentId, Message message) {

    }


    @Override
    Component doLoadComponent(Parser<Component> parser, ComponentDefinition definition) {
        Component instance = parser.createInstance(definition.getParams());
        // TODO: 2020/2/22 component共有参数 id name。。。
        return instance;
    }

    @Override
    Entry doLoadEntry(Parser<Entry> parser, EntryDefinition definition) {
        Entry instance = parser.createInstance(definition.getParams());
        // TODO: 2020/2/22 entry共有参数 id name。。。
        return instance;
    }
}
