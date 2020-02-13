package org.malacca.messaging.core;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.malacca.messaging.Message;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;

public abstract class AbstractMessageSendingTemplate<D> implements MessageSendingOptions<D> {
    protected final Log logger = LogFactory.getLog(this.getClass());
    @Nullable
    private D defaultDestination;

    public AbstractMessageSendingTemplate() {
    }

    public AbstractMessageSendingTemplate(@Nullable D defaultDestination) {
        this.defaultDestination = defaultDestination;
    }

    public void send(Message<?> message) {
        this.send(this.getDefaultDestination(), message);
    }

    public void send(D destination, Message<?> message) {
        this.doSend(destination, message);
    }

    protected abstract void doSend(D destination, Message<?> message);

    @Nullable
    public D getDefaultDestination() {
        Assert.state(this.defaultDestination != null, "No 'defaultDestination' configured");
        return defaultDestination;
    }

    public void setDefaultDestination(@Nullable D defaultDestination) {
        this.defaultDestination = defaultDestination;
    }
}
