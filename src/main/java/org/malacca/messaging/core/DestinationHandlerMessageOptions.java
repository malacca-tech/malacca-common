package org.malacca.messaging.core;

import org.malacca.exception.MessagingException;
import org.malacca.messaging.Message;

public interface DestinationHandlerMessageOptions<D> extends MessageReceivingOperations<D>, MessageRequestReplyOperations<D>, MessageSendingOptions<D> {
    void send(String channelName, Message<?> message) throws MessagingException;

    Message<?> receive(String channelName) throws MessagingException;

    Message<?> sendAndReceive(String channelName, Message<?> message);
}
