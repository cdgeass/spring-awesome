package io.github.cdgeass.awesome.springoauth2authorizationserver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author cdgeass
 * @since  2021-01-30
 */
@Component
public class CachedRegisteredClientRepository implements RegisteredClientRepository {

    private final Logger logger = LoggerFactory.getLogger(CachedRegisteredClientRepository.class);

    private final Map<String, RegisteredClient> idRegistrationMap;
    private final Map<String, RegisteredClient> clientIdRegistrationMap;

    private final ClientRepository clientRepository;

    public CachedRegisteredClientRepository(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
        idRegistrationMap = new ConcurrentHashMap<>();
        clientIdRegistrationMap = new ConcurrentHashMap<>();
    }

    @PostConstruct
    public void init() {
        refresh();
    }

    @Scheduled(cron = "0 0/1 * * * ?")
    public void scheduledRefresh() {
        refresh();
    }

    private void refresh() {
        List<Client> clients = clientRepository.findAll();
        if (CollectionUtils.isEmpty(clients)) {
            logger.info("not found any client");
            return;
        }

        for (Client client : clients) {
            logger.info("register client: {}", client);
            RegisteredClient registeredClient = RegisteredClient.withId(client.getId().toString())
                    .clientId(client.getClientId())
                    .clientSecret(client.getSecret())
                    .clientAuthenticationMethod(ClientAuthenticationMethod.BASIC)
                    .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                    .redirectUri("http://www.baidu.com")
                    .clientSettings(clientSettings -> clientSettings.requireUserConsent(false))
                    .build();
            idRegistrationMap.put(registeredClient.getId(), registeredClient);
            clientIdRegistrationMap.put(registeredClient.getClientId(), registeredClient);
        }
    }

    @Override
    public RegisteredClient findById(String id) {
        return idRegistrationMap.get(id);
    }

    @Override
    public RegisteredClient findByClientId(String clientId) {
        return clientIdRegistrationMap.get(clientId);
    }

}
