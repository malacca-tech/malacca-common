package org.malacca.messaging.core;

import org.malacca.exception.MessagingException;
import org.malacca.messaging.Message;
import org.springframework.lang.Nullable;

public interface MessageRequestReplyOperations<D> {
    @Nullable
    Message<?> sendAndReceive(Message<?> message) throws MessagingException;

    @Nullable
    Message<?> sendAndReceive(D destination, Message<?> message) throws MessagingException;
}
