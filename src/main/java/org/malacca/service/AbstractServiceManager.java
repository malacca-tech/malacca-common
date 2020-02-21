package org.malacca.service;

import org.malacca.exception.ServiceLoadException;
import org.malacca.messaging.Message;
import org.malacca.utils.YmlParserUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.logging.Logger;

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

    /**
     * 1.创建service实例
     * 2.
     * @param yml
     * @throws ServiceLoadException
     */
    @Override
    public void loadService(String yml) throws ServiceLoadException {
        Map<String,Object> serviceMap;
        try {
            serviceMap = YmlParserUtils.ymlToMap(yml);
        } catch (IOException e) {
            // TODO: 2020/2/21 log
           throw new ServiceLoadException("解析yml失败",e);
        }

        // TODO: 2020/2/21 线程问题
        if(serviceMap!=null){
            Service service = buildServiceInstance(serviceMap);
            List<Map<String,Object>> components = (List<Map<String, Object>>) serviceMap.get("components");
            components.forEach(map->{
                String type = String.valueOf(map.get("type"));
                service.loadComponent(map,type);
                service.loadFlow(String.valueOf(serviceMap.get("flow")));
            });
        }
    }

    // TODO: 2020/2/21 yml 实体类
    private Service buildServiceInstance(Map<String,Object> serviceMap){
        String serviceId = String.valueOf(serviceMap.get("serviceId"));
        String namespace = String.valueOf(serviceMap.get("namespace"));
        String version = String.valueOf(serviceMap.get("version"));
        String displayName = String.valueOf(serviceMap.get("displayName"));
        String description = String.valueOf(serviceMap.get("description"));
        DefaultService defaultService = new DefaultService();
        defaultService.setServiceId(serviceId);
        defaultService.setNamespace(namespace);
        defaultService.setDisplayName(displayName);
        defaultService.setDescription(description);
        defaultService.setVersion(version);
        Map env = (Map) serviceMap.get("env");
        defaultService.setEnv(env);
        return defaultService;
    }

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
