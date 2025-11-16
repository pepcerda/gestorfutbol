package com.jcerdar.gestorfutbol.persistence.dao;

import com.jcerdar.gestorfutbol.persistence.model.Patrocinador;
import com.jcerdar.gestorfutbol.persistence.model.Soci;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PatrocinadorDao extends JpaRepository<Patrocinador, Long>, PatrocinadorCustomDao  {

    @Query("select c from Patrocinador c where c.campanya.id = :idCampanya order by c.campanya.id asc")
    Page<Patrocinador> findAllByCampanyaOrderById(@Param("idCampanya") Long idCampanya, Pageable pageable);

    List<Patrocinador> findAllByCampanyaId(Long campanyaId);

    @Query("SELECT COALESCE(SUM(p.donacio), 0) FROM Patrocinador p WHERE p.estatPagament = :estatPagament AND p.campanya.id = :campanyaId")
    Long sumaDonacionsPerEstatICampanya(@Param("campanyaId") Long campanyaId, @Param("estatPagament") String estatPagament);


    @Query("SELECT COALESCE(SUM(p.donacio), 0) FROM Patrocinador p WHERE p.campanya.id = :campanyaId")
    Long sumaDonacionsPerCampanya(@Param("campanyaId") Long campanyaId);

    @Query("SELECT COALESCE(COUNT(p), 0) FROM Patrocinador p WHERE p.campanya.id = :campanyaId")
    Long countPatrocinadorsByCampanya(@Param("campanyaId") Long campanyaId);

}
