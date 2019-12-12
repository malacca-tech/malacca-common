package org.malacca.support;

import org.malacca.messaging.Message;

public interface MessageBuilderFactory {

    <T> AbstractIntegrationMessageBuilder<T> fromMessage(Message<T> message);

    <T> AbstractIntegrationMessageBuilder<T> withPayload(T payload);
}
