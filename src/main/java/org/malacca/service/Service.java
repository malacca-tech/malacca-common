package org.malacca.service;

import java.util.Map;

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
     * 服务id
     *
     * @param serviceId
     */
    void setServiceId(String serviceId);

    /**
     * 加载组件
     * params 组件内部参数
     * type 组件类型 根据类型 判断使用哪种解析器
     */
    void loadComponent(Map<String, Object> params, String type);

    /**
     * 加载流程 使用FlowBuilder 闯将Flow
     */
    void loadFlow(String flowStr);
}
