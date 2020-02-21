package org.malacca.support.parser;

import java.util.Map;

/**
 * 编译
 */
public interface Parser<T> {
    T createInstance(Map<String,Object> params) throws ClassNotFoundException;
}
