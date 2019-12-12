package org.malacca.messaging;

public interface SubscribableChannel extends MessageChannel {
    boolean subscribe(MessageHandler messageHandler);

    boolean unsubscribe(MessageHandler messageHandler);
}
