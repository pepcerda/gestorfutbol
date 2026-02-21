package com.jcerdar.gestorfutbol.persistence.dao;

import com.jcerdar.gestorfutbol.persistence.model.Directiva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DirectivaDao extends JpaRepository<Directiva, Long> {

    Directiva findDirectivaByDataBaixaIsNull();

    List<Directiva> findAllByOrderByDataAltaAsc();

    @Query("SELECT d FROM Directiva d WHERE d.tenant.id = :tenantId ORDER BY d.dataAlta ASC")
    List<Directiva> findAllByTenantIdOrderByDataAltaAsc(Long tenantId);
}
