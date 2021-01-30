package io.github.cdgeass.awesome.springoauth2authorizationserver;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.keys.KeyManager;
import org.springframework.security.crypto.keys.ManagedKey;
import org.springframework.security.crypto.keys.StaticKeyGeneratingKeyManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.server.authorization.client.InMemoryRegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.util.Set;

/**
 * @author cdgeass
 * @since 2021-01-28
 */
@Import(OAuth2AuthorizationServerConfiguration.class)
@EnableWebSecurity
@Configuration(proxyBeanMethods = false)
public class AuthorizationServerConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public KeyManager keyManager() {
        return new KeyManager() {
            @Override
            public ManagedKey findByKeyId(String keyId) {
                return null;
            }

            @Override
            public Set<ManagedKey> findByAlgorithm(String algorithm) {
                return null;
            }

            @Override
            public Set<ManagedKey> getKeys() {
                return null;
            }
        };
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .formLogin()
                .and()
                .authorizeRequests(authorizeRequests ->
                    authorizeRequests
                            .antMatchers("/login").permitAll()
                            .anyRequest().authenticated()
                );
    }
}
