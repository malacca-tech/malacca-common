package org.malacca.support;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.malacca.messaging.*;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class MessageBuilder<T> extends AbstractIntegrationMessageBuilder<T> {
    private static final Log logger = LogFactory.getLog(MessageBuilder.class);
    private final T payload;
    private final MessageHeaders messageHeaders;
    @Nullable
    private final Message<T> originalMessage;
    private volatile boolean modified;
    private String[] readOnlyHeaders;

    private MessageBuilder(T payload, Message<T> originalMessage) {
        Assert.notNull(payload, "payload must not be null");
        this.payload = payload;
        this.originalMessage = originalMessage;
        this.messageHeaders = new MessageHeaders(originalMessage);
        if (originalMessage != null) {
            this.modified = !this.payload.equals(originalMessage.getPayload());
        }

    }

    public T getPayload() {
        return this.payload;
    }

    public Map<String, Object> getHeaders() {
        return this.messageHeaders.getHeaders();
    }

    public static <T> MessageBuilder<T> fromMessage(Message<T> message) {
        Assert.notNull(message, "message must not be null");
        return new MessageBuilder(message.getPayload(), message);
    }

    public static <T> MessageBuilder<T> withPayload(T payload) {
        return new MessageBuilder(payload, (Message) null);
    }

    public MessageBuilder<T> setHeader(String headerName, @Nullable Object headerValue) {
        this.messageHeaders.setHeader(headerName, headerValue);
        return this;
    }

    public MessageBuilder<T> setHeaderIfAbsent(String headerName, Object headerValue) {
//        this.headerAccessor.setHeaderIfAbsent(headerName, headerValue);
        return this;
    }

    public MessageBuilder<T> removeHeaders(String... headerPatterns) {
//        this.messageHeaders.removeHeaders(headerPatterns);
        return this;
    }

    public MessageBuilder<T> removeHeader(String headerName) {
        return this;
    }

    public MessageBuilder<T> copyHeaders(@Nullable Map<String, ?> headersToCopy) {
        // TODO: 2019/11/16
//        this.headerAccessor.copyHeaders(headersToCopy);
        return this;
    }

    public MessageBuilder<T> copyHeadersIfAbsent(@Nullable Map<String, ?> headersToCopy) {
        if (headersToCopy != null) {
            Iterator var2 = headersToCopy.entrySet().iterator();

            while (var2.hasNext()) {
                Map.Entry<String, ?> entry = (Map.Entry) var2.next();
                String headerName = (String) entry.getKey();
            }
        }

        return this;
    }

    protected List<List<Object>> getSequenceDetails() {
//        return (List) this.headerAccessor.getHeader("sequenceDetails");
        return null;
    }

    protected Object getCorrelationId() {
//        return this.headerAccessor.getCorrelationId();
        return null;
    }

    protected Object getSequenceNumber() {
//        return this.headerAccessor.getSequenceNumber();
        return null;
    }

    protected Object getSequenceSize() {
//        return this.headerAccessor.getSequenceSize();
        return null;
    }

    public MessageBuilder<T> pushSequenceDetails(Object correlationId, int sequenceNumber, int sequenceSize) {
        super.pushSequenceDetails(correlationId, sequenceNumber, sequenceSize);
        return this;
    }

    public MessageBuilder<T> popSequenceDetails() {
        super.popSequenceDetails();
        return this;
    }

    public MessageBuilder<T> setExpirationDate(Long expirationDate) {
        super.setExpirationDate(expirationDate);
        return this;
    }

    public MessageBuilder<T> setExpirationDate(Date expirationDate) {
        super.setExpirationDate(expirationDate);
        return this;
    }

    public MessageBuilder<T> setCorrelationId(Object correlationId) {
        super.setCorrelationId(correlationId);
        return this;
    }

    public MessageBuilder<T> setReplyChannel(MessageChannel replyChannel) {
        super.setReplyChannel(replyChannel);
        return this;
    }

    public MessageBuilder<T> setReplyChannelName(String replyChannelName) {
        super.setReplyChannelName(replyChannelName);
        return this;
    }

    public MessageBuilder<T> setErrorChannel(MessageChannel errorChannel) {
        super.setErrorChannel(errorChannel);
        return this;
    }

    public MessageBuilder<T> setErrorChannelName(String errorChannelName) {
        super.setErrorChannelName(errorChannelName);
        return this;
    }

    public MessageBuilder<T> setSequenceNumber(Integer sequenceNumber) {
        super.setSequenceNumber(sequenceNumber);
        return this;
    }

    public MessageBuilder<T> setSequenceSize(Integer sequenceSize) {
        super.setSequenceSize(sequenceSize);
        return this;
    }

    public MessageBuilder<T> setPriority(Integer priority) {
        super.setPriority(priority);
        return this;
    }

    public MessageBuilder<T> readOnlyHeaders(String... readOnlyHeaders) {
        this.readOnlyHeaders = readOnlyHeaders;
//        this.headerAccessor.setReadOnlyHeaders(readOnlyHeaders);
        return this;
    }

    public Message<T> build() {
        if (!this.modified && this.originalMessage != null && !this.containsReadOnly(this.originalMessage.getHeaders())) {
            return this.originalMessage;
        } else {
            return (Message) (this.payload instanceof Throwable ? new ErrorMessage((Throwable) this.payload, this.messageHeaders.getHeaders()) : new GenericMessage(this.payload, this.messageHeaders.getHeaders()));
        }
    }

    private boolean containsReadOnly(MessageHeaders headers) {
        if (!ObjectUtils.isEmpty(this.readOnlyHeaders)) {
            String[] var2 = this.readOnlyHeaders;
            int var3 = var2.length;

            for (int var4 = 0; var4 < var3; ++var4) {
                String readOnly = var2[var4];
                if (headers.containsKey(readOnly)) {
                    return true;
                }
            }
        }

        return false;
    }
}
