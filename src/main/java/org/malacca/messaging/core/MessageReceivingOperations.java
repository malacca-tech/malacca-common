package org.malacca.messaging.core;

import org.malacca.exception.MessagingException;
import org.malacca.messaging.Message;
import org.springframework.lang.Nullable;

public interface MessageReceivingOperations<D> {
    @Nullable
    Message<?> receive() throws MessagingException;

    @Nullable
    Message<?> receive(D destination) throws MessagingException;
}
