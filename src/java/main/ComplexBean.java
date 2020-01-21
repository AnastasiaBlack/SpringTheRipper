package main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ComplexBean implements IBean{
    public SimpleBeanProviderService getSimpleBeanProviderService() {
        return simpleBeanProviderService;
    }

    public void setSimpleBeanProviderService(SimpleBeanProviderService simpleBeanProviderService) {
        this.simpleBeanProviderService = simpleBeanProviderService;
    }

    private SimpleBeanProviderService simpleBeanProviderService;


    public ComplexBean(SimpleBeanProviderService simpleBeanProviderService) {
        this.simpleBeanProviderService = simpleBeanProviderService;
    }

    @Override
    public void doSth() {
        System.out.println("Complex bean calls simple one and gets: " + simpleBeanProviderService.getSimpleBean().getDescription());
    }
}
