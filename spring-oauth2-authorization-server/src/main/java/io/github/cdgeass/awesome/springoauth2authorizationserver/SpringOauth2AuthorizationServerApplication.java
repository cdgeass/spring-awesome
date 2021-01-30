package io.github.cdgeass.awesome.springoauth2authorizationserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author cdgeass
 * @since  2021-01-28
 */
@EnableScheduling
@SpringBootApplication
public class SpringOauth2AuthorizationServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringOauth2AuthorizationServerApplication.class, args);
    }

}
