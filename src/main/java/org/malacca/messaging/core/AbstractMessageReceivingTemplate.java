package org.malacca.messaging.core;

import org.malacca.messaging.Message;
import org.springframework.lang.Nullable;

public abstract class AbstractMessageReceivingTemplate<D> extends AbstractMessageSendingTemplate<D> implements MessageReceivingOperations<D> {
    public AbstractMessageReceivingTemplate() {
    }

    @Nullable
    public Message<?> receive() {
        return this.doReceive(this.getDefaultDestination());
    }

    @Nullable
    public Message<?> receive(D destination) {
        return this.doReceive(destination);
    }

    @Nullable
    protected abstract Message<?> doReceive(D var1);
}
