package org.malacca.messaging;

import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;

import java.io.Serializable;
import java.util.Map;

public class GenericMessage<T> implements Message<T>, Serializable {
    private static final long serialVersionUID = 4268801052358035098L;
    private final T payload;
    private final Map<String, Object> context;

    public GenericMessage(T payload) {
        this(payload, null);
    }

    public GenericMessage(T payload, Map<String, Object> context) {
        Assert.notNull(payload, "Payload must not be null");
        Assert.notNull(context, "MessageHeaders must not be null");
        this.payload = payload;
        this.context = context;
    }

    public T getPayload() {
        return this.payload;
    }

    @Override
    public Map<String, Object> getContext() {
        return this.context;
    }


    public boolean equals(Object other) {
        if (this == other) {
            return true;
        } else if (!(other instanceof GenericMessage)) {
            return false;
        } else {
            GenericMessage<?> otherMsg = (GenericMessage) other;
            return ObjectUtils.nullSafeEquals(this.payload, otherMsg.payload) && this.context.equals(otherMsg.context);
        }
    }

    public int hashCode() {
        return ObjectUtils.nullSafeHashCode(this.payload) * 23 + this.context.hashCode();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(this.getClass().getSimpleName());
        sb.append(" [payload=");
        if (this.payload instanceof byte[]) {
            sb.append("byte[").append(((byte[]) ((byte[]) this.payload)).length).append("]");
        } else {
            sb.append(this.payload);
        }

        sb.append(", headers=").append(this.context).append("]");
        return sb.toString();
    }
}
