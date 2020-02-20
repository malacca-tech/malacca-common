package org.malacca.support.mapping;

import org.malacca.messaging.MessageHeaders;

import java.util.Map;

public interface HeaderMapper<T> {
    void fromHeaders(MessageHeaders var1, T var2);

    Map<String, Object> toHeaders(T var1);
}
