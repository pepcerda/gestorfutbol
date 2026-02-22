package com.jcerdar.gestorfutbol.persistence.dao;

import com.jcerdar.gestorfutbol.persistence.model.Tenant;
import com.jcerdar.gestorfutbol.persistence.model.Usuari;

import java.lang.StackWalker.Option;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuariDao extends JpaRepository<Usuari, Long> {

    Optional<Usuari> findByProviderId(String providerId);

    Optional<Usuari> findByEmail(String email); 

}
