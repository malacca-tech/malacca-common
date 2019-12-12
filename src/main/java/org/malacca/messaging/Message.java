package org.malacca.messaging;

public interface Message<T> {
    T getPayload();

    MessageHeaders getHeaders();
}
