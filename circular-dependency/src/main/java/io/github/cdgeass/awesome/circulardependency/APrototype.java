package io.github.cdgeass.awesome.circulardependency;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

/**
 * @author cdgeass
 * @since 2021-04-21
 */
@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class APrototype {

    @Autowired
    private BPrototype bPrototype;

    public BPrototype getbPrototype() {
        return bPrototype;
    }
}
