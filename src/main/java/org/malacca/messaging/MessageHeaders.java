package org.malacca.messaging;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.lang.Nullable;
import org.springframework.util.AlternativeJdkIdGenerator;
import org.springframework.util.IdGenerator;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.*;

public class MessageHeaders implements Map<String, Object>, Serializable {
    public static final UUID ID_VALUE_NONE = new UUID(0L, 0L);
    public static final String ID = "id";
    public static final String TIMESTAMP = "timestamp";
    public static final String CONTENT_TYPE = "contentType";
    public static final String REPLY_CHANNEL = "replyChannel";
    public static final String ERROR_CHANNEL = "errorChannel";
    private static final long serialVersionUID = 7035068984263400920L;
    private static final Logger logger = LogManager.getLogger(MessageHeaders.class);
    private static final IdGenerator defaultIdGenerator = new AlternativeJdkIdGenerator();
    @Nullable
    private static volatile IdGenerator idGenerator;
    private final Map<String, Object> headers;

    public MessageHeaders(@Nullable Map<String, Object> headers) {
        this(headers, (UUID) null, (Long) null);
    }

    public MessageHeaders(Message<?> originMessage) {
        this(originMessage.getHeaders().headers, null, null);
    }

    protected MessageHeaders(@Nullable Map<String, Object> headers, @Nullable UUID id, @Nullable Long timestamp) {
        this.headers = headers != null ? new HashMap(headers) : new HashMap();
        if (id == null) {
            this.headers.put("id", getIdGenerator().generateId());
        } else if (id == ID_VALUE_NONE) {
            this.headers.remove("id");
        } else {
            this.headers.put("id", id);
        }

        if (timestamp == null) {
            this.headers.put("timestamp", System.currentTimeMillis());
        } else if (timestamp < 0L) {
            this.headers.remove("timestamp");
        } else {
            this.headers.put("timestamp", timestamp);
        }

    }

    private MessageHeaders(MessageHeaders original, Set<String> keysToIgnore) {
        this.headers = new HashMap(original.headers.size());
        original.headers.forEach((key, value) -> {
            if (!keysToIgnore.contains(key)) {
                this.headers.put(key, value);
            }

        });
    }

    protected Map<String, Object> getRawHeaders() {
        return this.headers;
    }

    protected static IdGenerator getIdGenerator() {
        IdGenerator generator = idGenerator;
        return generator != null ? generator : defaultIdGenerator;
    }

    @Nullable
    public UUID getId() {
        return (UUID) this.get("id", UUID.class);
    }

    @Nullable
    public Long getTimestamp() {
        return (Long) this.get("timestamp", Long.class);
    }

    @Nullable
    public Object getReplyChannel() {
        return this.get("replyChannel");
    }

    @Nullable
    public Object getErrorChannel() {
        return this.get("errorChannel");
    }

    @Nullable
    public <T> T get(Object key, Class<T> type) {
        Object value = this.headers.get(key);
        if (value == null) {
            return null;
        } else if (!type.isAssignableFrom(value.getClass())) {
            throw new IllegalArgumentException("Incorrect type specified for header '" + key + "'. Expected [" + type + "] but actual type is [" + value.getClass() + "]");
        } else {
            return (T) value;
        }
    }

    public boolean containsKey(Object key) {
        return this.headers.containsKey(key);
    }

    public boolean containsValue(Object value) {
        return this.headers.containsValue(value);
    }

    public Set<Entry<String, Object>> entrySet() {
        return Collections.unmodifiableMap(this.headers).entrySet();
    }

    @Nullable
    public Object get(Object key) {
        return this.headers.get(key);
    }

    public boolean isEmpty() {
        return this.headers.isEmpty();
    }

    public Set<String> keySet() {
        return Collections.unmodifiableSet(this.headers.keySet());
    }

    public int size() {
        return this.headers.size();
    }

    public Collection<Object> values() {
        return Collections.unmodifiableCollection(this.headers.values());
    }

    public Object put(String key, Object value) {
        throw new UnsupportedOperationException("MessageHeaders is immutable");
    }

    public void putAll(Map<? extends String, ? extends Object> map) {
        throw new UnsupportedOperationException("MessageHeaders is immutable");
    }

    public Object remove(Object key) {
        throw new UnsupportedOperationException("MessageHeaders is immutable");
    }

    public void clear() {
        throw new UnsupportedOperationException("MessageHeaders is immutable");
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        Set<String> keysToIgnore = new HashSet();
        this.headers.forEach((key, value) -> {
            if (!(value instanceof Serializable)) {
                keysToIgnore.add(key);
            }

        });
        if (keysToIgnore.isEmpty()) {
            out.defaultWriteObject();
        } else {
            if (logger.isDebugEnabled()) {
                logger.debug("Ignoring non-serializable message headers: " + keysToIgnore);
            }

            out.writeObject(new MessageHeaders(this, keysToIgnore));
        }

    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
    }

    public boolean equals(Object other) {
        return this == other || other instanceof MessageHeaders && this.headers.equals(((MessageHeaders) other).headers);
    }

    public int hashCode() {
        return this.headers.hashCode();
    }

    public Map<String, Object> getHeaders() {
        return headers;
    }

    public void setHeader(String key, Object value) {
        this.headers.put(key, value);
    }

    public void removeHeader(String key, Object value) {
        this.headers.remove(key, value);
    }

    public void removeHeader(String key) {
        this.headers.remove(key);
    }

    public String toString() {
        return this.headers.toString();
    }
}
