package main.configuration;

import main.annotation.DeprecatedClass;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

public class DeprecationHandlerBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
        String[] beanDefinitions = configurableListableBeanFactory.getBeanDefinitionNames();
        for (String beanName : beanDefinitions) {
            BeanDefinition beanDefinition = configurableListableBeanFactory.getBeanDefinition(beanName);
            String className = beanDefinition.getBeanClassName();
            try {
                Class beanClass = Class.forName(className);
                DeprecatedClass annotation = (DeprecatedClass) beanClass.getAnnotation(DeprecatedClass.class);
                if (annotation != null) {
                    beanDefinition.setBeanClassName(annotation.newImplementation().getName());
                }

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
