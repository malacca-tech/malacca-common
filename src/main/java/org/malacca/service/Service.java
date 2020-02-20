package org.malacca.service;

/**
 * <p>
 * Title :
 * </p>
 * <p>
 * Description: 加载组件，元数据管理， 编排消息传递，提供日志功能
 * </p>
 * <p>
 * Author :chensheng 2020/2/20
 * </p>
 * <p>
 * Department :
 * </p>
 */
public interface Service {

    /**
     * 创建服务
     *
     * @param yml 服务的配置文件
     */
    void createService(String yml);

    /**
     * 服务id
     *
     * @param serviceId
     */
    void setServiceId(String serviceId);
}