package org.malacca.messaging.core;

import org.malacca.exception.MessagingException;
import org.malacca.messaging.Message;

public abstract class AbstractMessageTemplate<D> extends AbstractMessageReceivingTemplate<D> implements MessageRequestReplyOperations<D> {
    public AbstractMessageTemplate() {
    }

    @Override
    public Message<?> sendAndReceive(Message<?> message) throws MessagingException {
        return this.doSendAndReceive(this.getDefaultDestination(), message);
    }

    @Override
    public Message<?> sendAndReceive(D destination, Message<?> message) throws MessagingException {
        return this.doSendAndReceive(destination, message);
    }

    abstract protected Message<?> doSendAndReceive(D destination, Message<?> message) throws MessagingException;
}
