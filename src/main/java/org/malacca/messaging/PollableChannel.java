package org.malacca.messaging;

public interface PollableChannel extends MessageChannel {
    Message<?> receive();

    Message<?> receive(long timeout);
}
