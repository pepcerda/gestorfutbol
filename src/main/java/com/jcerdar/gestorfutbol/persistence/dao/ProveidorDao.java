package com.jcerdar.gestorfutbol.persistence.dao;

import com.jcerdar.gestorfutbol.persistence.model.Proveidor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProveidorDao extends JpaRepository<Proveidor, Long> {

    Page<Proveidor> findAll(Pageable pageable);
}
