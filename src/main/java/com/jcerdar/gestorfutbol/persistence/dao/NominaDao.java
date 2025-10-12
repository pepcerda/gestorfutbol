package com.jcerdar.gestorfutbol.persistence.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.method.P;

import com.jcerdar.gestorfutbol.persistence.model.Nomina;
import com.jcerdar.gestorfutbol.persistence.model.type.EstatPagament;

public interface NominaDao extends JpaRepository<Nomina, Long> {

    @Query("SELECT SUM(p.quantitat) FROM Nomina p WHERE p.mensualitat.campanya.id = :campanyaId AND p.estatPagament = :estatPagament")
    Long sumaNominesPagadesPerCampanya(@Param("campanyaId") Long campanyaId, @Param("estatPagament") EstatPagament estatPagament);

}
