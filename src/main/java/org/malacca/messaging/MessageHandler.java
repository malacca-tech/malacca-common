package org.malacca.messaging;

import org.malacca.exception.MessagingException;

public interface MessageHandler {
    void handMessage(Message<?> message) throws MessagingException;
}
