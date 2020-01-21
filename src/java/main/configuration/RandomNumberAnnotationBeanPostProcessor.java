package main.configuration;

import main.annotation.RandomNumber;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class RandomNumberAnnotationBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object o, String s) throws BeansException {
        List<Field> fields = Arrays.stream(o.getClass().getDeclaredFields()).filter(f -> f.isAnnotationPresent(RandomNumber
                .class)).collect(Collectors.toList());
        if (!fields.isEmpty()) {
            for(Field field : fields) {
                field.setAccessible(true);
                RandomNumber annotation = field.getAnnotation(RandomNumber.class);
                try {
                    field.set(o, Math.random()*(annotation.max()-annotation.min()) + annotation.min());
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return o;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}
