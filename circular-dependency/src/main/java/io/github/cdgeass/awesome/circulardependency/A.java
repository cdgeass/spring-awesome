package io.github.cdgeass.awesome.circulardependency;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author cdgeass
 * @since 2021-04-21
 */
@Component
public class A {

    @Autowired
    private B b;

}
