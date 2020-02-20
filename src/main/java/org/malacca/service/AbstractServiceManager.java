package org.malacca.service;

import org.malacca.messaging.Message;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * <p>
 * Title :
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Author :chensheng 2020/2/20
 * </p>
 * <p>
 * Department :
 * </p>
 */
public abstract class AbstractServiceManager implements ServiceManager {

    /**
     * 服务缓存
     */
    private Map<String, Service> serviceMap;

    /**
     * 线程池
     */
    private ThreadPoolExecutor threadExecutor;


    /**
     * 提供重新发布功能
     * serviceId 服务id
     * componentId 组件id 用于定位从什么位置开始进行重推操作
     * message 请求消息
     */
    abstract void retryFrom(String serviceId, String componentId, Message message);

    public Map<String, Service> getServiceMap() {
        if (this.serviceMap == null) {
            this.serviceMap = new HashMap<>();
        }
        return serviceMap;
    }

    // TODO: 2020/2/20 连接池初始化
    public ThreadPoolExecutor getThreadExecutor() {
        return threadExecutor;
    }

    public void setThreadExecutor(ThreadPoolExecutor threadExecutor) {
        this.threadExecutor = threadExecutor;
    }
}
