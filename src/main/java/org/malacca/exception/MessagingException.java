package org.malacca.exception;

import org.malacca.messaging.Message;
import org.springframework.core.NestedRuntimeException;
import org.springframework.lang.Nullable;

public class MessagingException extends NestedRuntimeException {
    @Nullable
    private final Message<?> failedMessage;

    public MessagingException(Message<?> message) {
        super((String) null, (Throwable) null);
        this.failedMessage = message;
    }

    public MessagingException(String description) {
        super(description);
        this.failedMessage = null;
    }

    public MessagingException(@Nullable String description, @Nullable Throwable cause) {
        super(description, cause);
        this.failedMessage = null;
    }

    public MessagingException(Message<?> message, String description) {
        super(description);
        this.failedMessage = message;
    }

    public MessagingException(Message<?> message, Throwable cause) {
        super((String) null, cause);
        this.failedMessage = message;
    }

    public MessagingException(Message<?> message, @Nullable String description, @Nullable Throwable cause) {
        super(description, cause);
        this.failedMessage = message;
    }

    @Nullable
    public Message<?> getFailedMessage() {
        return this.failedMessage;
    }

    public String toString() {
        return super.toString() + (this.failedMessage == null ? "" : ", failedMessage=" + this.failedMessage);
    }
}
