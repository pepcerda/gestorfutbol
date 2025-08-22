package com.jcerdar.gestorfutbol.persistence.dao;

import com.jcerdar.gestorfutbol.persistence.model.Soci;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SociDao extends JpaRepository<Soci, Long> {

    @Query("select c from Soci c where c.campanya.id = :idCampanya order by c.id asc")
    Page<Soci> findAllByCampanyaOrderById(@Param("idCampanya") Long idCampanya, Pageable pageable);

    List<Soci> findAllByCampanyaId(Long campanyaId);

    @Query("SELECT COALESCE(MAX(s.idSoci), 0) FROM Soci s WHERE s.campanya.id = :campanyaId")
    Integer findMaxIdSociByCampanya(@Param("campanyaId") Long campanyaId);


    @Query("""
        SELECT SUM(ts.cuota)
        FROM Soci s
        JOIN s.tipoSoci ts
        WHERE s.campanya.id = :campanyaId
          AND s.estatPagament = :estatPagament
    """)
    Long totalRecaudacioPerCampanyaIEstat(@Param("campanyaId") Long campanyaId,
                                          @Param("estatPagament") String estatPagament);

    @Query("""
        SELECT SUM(ts.cuota)
        FROM Soci s
        JOIN s.tipoSoci ts
        WHERE s.campanya.id = :campanyaId
    """)
    Long totalRecaudacioPerCampanya(@Param("campanyaId") Long campanyaId);

}
