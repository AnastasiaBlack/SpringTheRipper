package main;

import main.quoter.Quoter;
import main.quoter.impl.TerminatorQuoter;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        Quoter terminatorQuoter = (Quoter) context.getBean("terminatorQuoter");
        terminatorQuoter.sayQuote();
        ComplexBean cb = (ComplexBean) context.getBean("complexBean");
        cb.doSth();
        cb.doSth();
        cb.doSth();
        cb.doSth();
    }
}
