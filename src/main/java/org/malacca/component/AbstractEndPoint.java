package org.malacca.component;

import org.malacca.context.IntegrationObjectSupport;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.SmartLifecycle;

public abstract class AbstractEndPoint extends IntegrationObjectSupport implements SmartLifecycle, DisposableBean {

}
