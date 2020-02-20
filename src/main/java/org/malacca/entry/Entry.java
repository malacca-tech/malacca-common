package org.malacca.entry;

import org.malacca.messaging.Message;

public interface Entry {
    Message handleMessage(Message<?> message);

    void setId(String componentId);
}
