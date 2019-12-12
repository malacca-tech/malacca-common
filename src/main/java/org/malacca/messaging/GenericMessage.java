package org.malacca.messaging;

import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;

import java.io.Serializable;
import java.util.Map;

public class GenericMessage<T> implements Message<T>, Serializable {
    private static final long serialVersionUID = 4268801052358035098L;
    private final T payload;
    private final MessageHeaders headers;

    public GenericMessage(T payload) {
        this(payload, new MessageHeaders((Map)null));
    }

    public GenericMessage(T payload, Map<String, Object> headers) {
        this(payload, new MessageHeaders(headers));
    }

    public GenericMessage(T payload, MessageHeaders headers) {
        Assert.notNull(payload, "Payload must not be null");
        Assert.notNull(headers, "MessageHeaders must not be null");
        this.payload = payload;
        this.headers = headers;
    }

    public T getPayload() {
        return this.payload;
    }

    public MessageHeaders getHeaders() {
        return this.headers;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        } else if (!(other instanceof GenericMessage)) {
            return false;
        } else {
            GenericMessage<?> otherMsg = (GenericMessage)other;
            return ObjectUtils.nullSafeEquals(this.payload, otherMsg.payload) && this.headers.equals(otherMsg.headers);
        }
    }

    public int hashCode() {
        return ObjectUtils.nullSafeHashCode(this.payload) * 23 + this.headers.hashCode();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(this.getClass().getSimpleName());
        sb.append(" [payload=");
        if (this.payload instanceof byte[]) {
            sb.append("byte[").append(((byte[])((byte[])this.payload)).length).append("]");
        } else {
            sb.append(this.payload);
        }

        sb.append(", headers=").append(this.headers).append("]");
        return sb.toString();
    }
}
