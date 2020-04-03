package com.hengtong.led.mybatisPlus.factory;

import com.hengtong.led.mybatisPlus.service.TestFactoryService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class HandlerBeanFactory implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    /**
     * 根据key获取处理器
     *
     * @param key
     * @return
     */
    public <T> Optional<TestFactoryService> getHandler(String key, Class<T> clz) {
        T obj;
        try {
            obj = clz.cast(applicationContext.getBean(key));
        } catch (NoSuchBeanDefinitionException nsbde) {
            obj = null;
        }
        if (obj == null) {
            newHandler(key, clz);
            obj = clz.cast(applicationContext.getBean(key));
        }
        return obj == null ? Optional.empty() : Optional.of((TestFactoryService) obj);
    }

    /**
     * 创建handler
     *
     * @param key
     * @param handlerClz
     */
    protected void newHandler(String key, Class handlerClz) {

        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(handlerClz);

        BeanDefinition beanDefinition = beanDefinitionBuilder.getRawBeanDefinition();

        BeanDefinitionRegistry beanFactory = (BeanDefinitionRegistry) ((ConfigurableApplicationContext) applicationContext).getBeanFactory();
        beanFactory.registerBeanDefinition(key, beanDefinition);
    }
}
