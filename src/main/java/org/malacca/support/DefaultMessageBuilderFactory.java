package org.malacca.support;

import org.malacca.messaging.Message;

public class DefaultMessageBuilderFactory implements MessageBuilderFactory{

    @Override
    public <T> AbstractIntegrationMessageBuilder<T> fromMessage(Message<T> message) {
        return MessageBuilder.fromMessage(message);
    }

    @Override
    public <T> AbstractIntegrationMessageBuilder<T> withPayload(T payload) {
        return MessageBuilder.withPayload(payload);
    }
}
