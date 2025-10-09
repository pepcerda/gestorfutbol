package com.jcerdar.gestorfutbol.persistence.dao;

import com.jcerdar.gestorfutbol.persistence.model.CaixaFixa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;

public interface CaixaFixaDao extends JpaRepository<CaixaFixa, Long>, CaixaFixaCustomDao {

    @Query("select c from CaixaFixa c where c.campanya.id = :idCampanya order by c.id asc")
    Page<CaixaFixa> findAllByCampanyaOrderById(@Param("idCampanya") Long idCampanya, Pageable pageable);

    @Query("SELECT SUM(p.despesa) FROM CaixaFixa p WHERE p.estat = :estat AND p.campanya.id = :campanyaId")
    BigDecimal sumaFacturesPerEstatICampanya(@Param("campanyaId") Long campanyaId, @Param("estat") String estat);


    @Query("SELECT SUM(p.despesa) FROM CaixaFixa p WHERE p.campanya.id = :campanyaId")
    BigDecimal sumaFacturesPerCampanya(@Param("campanyaId") Long campanyaId);

    @Query("SELECT COUNT(p) FROM CaixaFixa p WHERE p.campanya.id = :campanyaId")
    Long countFacturesByCampanya(@Param("campanyaId") Long campanyaId);


}
