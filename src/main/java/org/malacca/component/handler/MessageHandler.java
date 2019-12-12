package org.malacca.component.handler;

import org.malacca.messaging.Message;
import org.malacca.exception.MessagingException;

public interface MessageHandler {
    void handleMessage(Message<?> message) throws MessagingException;
}
