package main.configuration;

import main.annotation.Profiling;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

public class ProfilingHandlerBeanPostProcessor implements BeanPostProcessor {
    private Map<String, Class> classesWithAnnotationMap = new HashMap<>();
    private ProfilingFeatureSwitcher profilingFeatureSwitcher = new ProfilingFeatureSwitcher();

    public ProfilingHandlerBeanPostProcessor() {
        MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();
        try {
            mBeanServer.registerMBean(profilingFeatureSwitcher, new ObjectName("profiling", "name", "profilingFeatureSwitcher"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        Class beanClass = bean.getClass();
        if (beanClass.isAnnotationPresent(Profiling.class)) {
            classesWithAnnotationMap.put(beanName, beanClass);
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Class beanClass = classesWithAnnotationMap.get(beanName);
        if (beanClass != null && profilingFeatureSwitcher.isProfilingFeatureEnabled()) {
            return Proxy.newProxyInstance(beanClass.getClassLoader(), beanClass.getInterfaces(), new InvocationHandler() {
                @Override
                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                    System.out.println("Profiling started...");
                    long beforeTime = System.currentTimeMillis();
                    Object originalMethodInvocation = method.invoke(bean, args);
                    long afterTime = System.currentTimeMillis();
                    System.out.println("Finished profiling in " + (afterTime - beforeTime) + " miliseconds");
                    return originalMethodInvocation;
                }
            });
        }

        return bean;
    }
}
