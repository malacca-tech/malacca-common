package org.malacca.support.parser;

import org.malacca.utils.BeanFactoryUtils;

import java.lang.reflect.Field;
import java.util.Map;

public abstract class AbstractParser<T> implements Parser<T>{

    private String className;

    public AbstractParser(String className) {
        this.className = className;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    /**
     * 通用的 解析器
     */
    @Override
    public T createInstance(Map<String, Object> params) throws ClassNotFoundException {
        Class cl = Class.forName(className);
        Object newInstance = BeanFactoryUtils.newInstance(cl);
        Map<String,Object> paramMap = (Map<String, Object>) params.get("params");
        paramMap.forEach((key,value)->{
            params.put(key,value);
        });
        Field[] fields = cl.getFields();
        for (Field field : fields) {
            BeanFactoryUtils.setField(newInstance,field,params.get(field.getName()));
        }
        return (T) newInstance;
    }
}