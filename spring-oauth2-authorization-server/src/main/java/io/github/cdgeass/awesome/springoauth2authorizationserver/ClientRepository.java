package io.github.cdgeass.awesome.springoauth2authorizationserver;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author cdgeass
 * @since  2021-01-30
 */
public interface ClientRepository extends JpaRepository<Client, Long> {
}
