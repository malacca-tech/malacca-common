package org.malacca.messaging.core;

import org.malacca.exception.MessagingException;

public interface DestinationHandler<D> {
    D resolveDestination(String channelName) throws MessagingException;
}
