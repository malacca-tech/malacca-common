package org.malacca.messaging;

import org.springframework.lang.Nullable;

import java.util.Map;

public class ErrorMessage extends GenericMessage<Throwable> {

    private static final long serialVersionUID = -5470210965279837728L;
    @Nullable
    private final Message<?> originalMessage;

    public ErrorMessage(Throwable payload) {
        super(payload);
        this.originalMessage = null;
    }

    public ErrorMessage(Throwable payload, Map<String, Object> headers) {
        super(payload, headers);
        this.originalMessage = null;
    }

    public ErrorMessage(Throwable payload, MessageHeaders headers) {
        super(payload, headers);
        this.originalMessage = null;
    }

    public ErrorMessage(Throwable payload, Message<?> originalMessage) {
        super(payload);
        this.originalMessage = originalMessage;
    }

    public ErrorMessage(Throwable payload, Map<String, Object> headers, Message<?> originalMessage) {
        super(payload, headers);
        this.originalMessage = originalMessage;
    }

    public ErrorMessage(Throwable payload, MessageHeaders headers, Message<?> originalMessage) {
        super(payload, headers);
        this.originalMessage = originalMessage;
    }

    @Nullable
    public Message<?> getOriginalMessage() {
        return this.originalMessage;
    }

    public String toString() {
        return this.originalMessage == null ? super.toString() : super.toString() + " for original " + this.originalMessage;
    }
}
