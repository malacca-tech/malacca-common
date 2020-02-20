package org.malacca.component;

import org.malacca.messaging.Message;
import org.malacca.exception.MessagingException;

public interface Component {
    void handleMessage(Message<?> message) throws MessagingException;

    void setId(String componentId);
}
