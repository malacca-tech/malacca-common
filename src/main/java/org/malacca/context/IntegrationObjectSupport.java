package org.malacca.context;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.malacca.messaging.MessageChannel;
import org.malacca.support.DefaultMessageBuilderFactory;
import org.malacca.support.MessageBuilderFactory;
import org.malacca.support.context.NamedComponent;
import org.springframework.aop.TargetSource;
import org.springframework.aop.framework.Advised;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.util.Properties;

// TODO: 2019/11/16 表达式选型 spel，freemarker，etc
// TODO: 2019/11/16 channel ?
public class IntegrationObjectSupport implements BeanNameAware, NamedComponent, ApplicationContextAware, BeanFactoryAware, InitializingBean {

    protected static final ExpressionParser EXPRESSION_PARSER = new SpelExpressionParser();
    protected final Log logger = LogFactory.getLog(this.getClass());
    private final ConversionService defaultConversionService = DefaultConversionService.getSharedInstance();
    //    private DestinationResolver<MessageChannel> channelResolver;
    private String beanName;
    private String componentName;
    private BeanFactory beanFactory;
    private TaskScheduler taskScheduler;
    private ConversionService conversionService;
    private ApplicationContext applicationContext;
    private MessageBuilderFactory messageBuilderFactory;
    private Expression expression;
    private boolean initialized;

    public IntegrationObjectSupport() {
    }

    public final void setBeanName(String beanName) {
        this.beanName = beanName;
    }

    public String getComponentName() {
        return StringUtils.hasText(this.componentName) ? this.componentName : this.beanName;
    }

    public void setComponentName(String componentName) {
        this.componentName = componentName;
    }

    public String getComponentType() {
        return null;
    }

    public void setBeanFactory(BeanFactory beanFactory) {
        Assert.notNull(beanFactory, "'beanFactory' must not be null");
        this.beanFactory = beanFactory;
    }

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        Assert.notNull(applicationContext, "'applicationContext' must not be null");
        this.applicationContext = applicationContext;
    }

//    public void setChannelResolver(DestinationResolver<MessageChannel> channelResolver) {
//        Assert.notNull(channelResolver, "'channelResolver' must not be null");
//        this.channelResolver = channelResolver;
//    }

    public Expression getExpression() {
        return this.expression;
    }

    public final void setPrimaryExpression(Expression expression) {
        this.expression = expression;
    }

    public final void afterPropertiesSet() {
//        this.integrationProperties = IntegrationContextUtils.getIntegrationProperties(this.beanFactory);

        try {
            if (this.messageBuilderFactory == null) {
                if (this.beanFactory != null) {
//                    this.messageBuilderFactory = IntegrationUtils.getMessageBuilderFactory(this.beanFactory);
                } else {
                    this.messageBuilderFactory = new DefaultMessageBuilderFactory();
                }
            }

            this.onInit();
        } catch (Exception var2) {
            if (var2 instanceof RuntimeException) {
                throw (RuntimeException) var2;
            }

            throw new BeanInitializationException("failed to initialize", var2);
        }

        this.initialized = true;
    }

    protected void onInit() throws Exception {
    }

    protected boolean isInitialized() {
        return this.initialized;
    }

    protected BeanFactory getBeanFactory() {
        return this.beanFactory;
    }

    protected TaskScheduler getTaskScheduler() {
        if (this.taskScheduler == null && this.beanFactory != null) {
//            this.taskScheduler = IntegrationContextUtils.getTaskScheduler(this.beanFactory);
        }

        return this.taskScheduler;
    }

//    protected DestinationResolver<MessageChannel> getChannelResolver() {
//        if (this.channelResolver == null) {
//            this.channelResolver = new BeanFactoryChannelResolver(this.beanFactory);
//        }
//
//        return this.channelResolver;
//    }

    protected void setTaskScheduler(TaskScheduler taskScheduler) {
        Assert.notNull(taskScheduler, "taskScheduler must not be null");
        this.taskScheduler = taskScheduler;
    }

    public ConversionService getConversionService() {
        if (this.conversionService == null && this.beanFactory != null) {
//            this.conversionService = IntegrationUtils.getConversionService(this.beanFactory);
            if (this.conversionService == null && this.logger.isDebugEnabled()) {
                this.logger.debug("Unable to attempt conversion of Message payload types. Component '" + this.getComponentName() + "' has no explicit ConversionService reference, and there is no 'integrationConversionService' bean within the context.");
            }
        }

        return this.conversionService;
    }

    protected void setConversionService(ConversionService conversionService) {
        this.conversionService = conversionService;
    }

    public String getApplicationContextId() {
        return this.applicationContext == null ? null : this.applicationContext.getId();
    }

    protected ApplicationContext getApplicationContext() {
        return this.applicationContext;
    }

//    protected Properties getIntegrationProperties() {
//        return this.integrationProperties;
//    }

    protected MessageBuilderFactory getMessageBuilderFactory() {
        if (this.messageBuilderFactory == null) {
            this.messageBuilderFactory = new DefaultMessageBuilderFactory();
        }

        return this.messageBuilderFactory;
    }

    public void setMessageBuilderFactory(MessageBuilderFactory messageBuilderFactory) {
        this.messageBuilderFactory = messageBuilderFactory;
    }

//    protected <T> T getIntegrationProperty(String key, Class<T> tClass) {
//        return this.defaultConversionService.convert(this.integrationProperties.getProperty(key), tClass);
//    }

    protected <T> T extractTypeIfPossible(Object targetObject, Class<T> expectedType) {
        if (targetObject == null) {
            return null;
        } else if (expectedType.isAssignableFrom(targetObject.getClass())) {
            return (T) targetObject;
        } else if (targetObject instanceof Advised) {
            TargetSource targetSource = ((Advised) targetObject).getTargetSource();
            if (targetSource == null) {
                return null;
            } else {
                try {
                    return this.extractTypeIfPossible(targetSource.getTarget(), expectedType);
                } catch (Exception var5) {
                    throw new IllegalStateException(var5);
                }
            }
        } else {
            return null;
        }
    }

    public String toString() {
        return this.beanName != null ? this.beanName : super.toString();
    }
}
