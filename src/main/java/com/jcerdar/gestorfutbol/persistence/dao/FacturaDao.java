package com.jcerdar.gestorfutbol.persistence.dao;

import com.jcerdar.gestorfutbol.persistence.model.CaixaFixa;
import com.jcerdar.gestorfutbol.persistence.model.Factura;
import com.jcerdar.gestorfutbol.persistence.model.type.EstatPagament;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;

public interface FacturaDao extends JpaRepository<Factura, Long>, FacturaCustomDao {

    @Query("select c from Factura c where c.campanya.id = :idCampanya order by c.id asc")
    Page<Factura> findAllByCampanyaOrderById(@Param("idCampanya") Long idCampanya, Pageable pageable);

    @Query("SELECT COALESCE(SUM(p.despesa), 0) FROM Factura p WHERE p.estatPagament = :estatPagament AND p.campanya.id = :campanyaId")
    BigDecimal sumaFacturesPerEstatICampanya(@Param("campanyaId") Long campanyaId, @Param("estatPagament") EstatPagament estatPagament);

    @Query("SELECT COALESCE(SUM(p.despesa), 0) FROM Factura p WHERE p.campanya.id = :campanyaId")
    BigDecimal sumaFacturesPerCampanya(@Param("campanyaId") Long campanyaId);

    @Query("SELECT COALESCE(COUNT(p), 0) FROM Factura p WHERE p.campanya.id = :campanyaId")
    Long countFacturesByCampanya(@Param("campanyaId") Long campanyaId);
}
