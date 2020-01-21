package main;

import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Service;

@Service
public class SimpleBeanProviderService {
    @Lookup
    public SimpleBean getSimpleBean() {
        return null;
    };

}
