package main;

import main.quoter.Quoter;
import org.springframework.beans.factory.support.PropertiesBeanDefinitionReader;
import org.springframework.context.support.GenericApplicationContext;

public class PropertyFileApplicationContext extends GenericApplicationContext {
    public PropertyFileApplicationContext(String fileName) {
        PropertiesBeanDefinitionReader reader = new PropertiesBeanDefinitionReader(this);
        int definitionsCount = reader.loadBeanDefinitions(fileName);
        System.out.println("Found " + definitionsCount + " bean definitions.");
        refresh();
    }

    public static void main(String [] args) {
        PropertyFileApplicationContext context = new PropertyFileApplicationContext("context.properties");
        context.getBean(Quoter.class).sayQuote();
    }
}
