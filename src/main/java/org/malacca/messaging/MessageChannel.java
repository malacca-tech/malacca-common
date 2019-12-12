package org.malacca.messaging;

@FunctionalInterface
public interface MessageChannel {

    long INDEFINITE_TIMEOUT = -1L;

    default boolean send(Message<?> message) {
        return this.send(message, -1L);
    }

    boolean send(Message<?> message, long timeout);

}
