package org.malacca.messaging.core;

import org.malacca.messaging.Message;
import org.springframework.lang.Nullable;

public interface MessageSendingOptions<D> {
    @Nullable
    void send(Message<?> message);

    @Nullable
    void send(D d, Message<?> message);
}
