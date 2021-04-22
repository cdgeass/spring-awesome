package io.github.cdgeass.awesome.circulardependency;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author cdgeass
 * @since 2021-04-21
 */
public class CircularDependencyApplication {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        // prototype circular dependency
        applicationContext.register(A.class, B.class);

        applicationContext.refresh();
        System.out.println("applicationContext refreshed");

        applicationContext.close();
    }

}
