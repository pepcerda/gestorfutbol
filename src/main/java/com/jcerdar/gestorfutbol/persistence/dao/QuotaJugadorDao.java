package com.jcerdar.gestorfutbol.persistence.dao;

import com.jcerdar.gestorfutbol.persistence.model.QuotaJugador;
import com.jcerdar.gestorfutbol.persistence.model.type.EstatPagament;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface QuotaJugadorDao extends JpaRepository<QuotaJugador, Long>, QuotaJugadorCustomDao {


    @Query("""
                SELECT COALESCE(SUM(j.equip.quota), 0)
                FROM QuotaJugador s
                JOIN s.jugador j
                WHERE j.campanya.id = :campanyaId
                  AND j.equip.id = :equipId          
                  AND s.estatPagament = :estatPagament
            """)
    Long totalRecaudacioPerCampanyaIEquipIEstat(@Param("campanyaId") Long campanyaId, @Param("equipId") Long equipId,
                                          @Param("estatPagament") EstatPagament estatPagament);

    @Query("""
                SELECT COALESCE(SUM(ts.equip.quota), 0)
                FROM QuotaJugador s
                JOIN s.jugador ts
                WHERE ts.campanya.id = :campanyaId
                AND ts.equip.id = :equipId
            """)
    Long totalRecaudacioPerCampanyaIEquip(@Param("campanyaId") Long campanyaId, @Param("equipId") Long equipId);

        @Query("""
                SELECT COALESCE(SUM(ts.equip.quota), 0)
                FROM QuotaJugador s
                JOIN s.jugador ts
                WHERE ts.campanya.id = :campanyaId
                and s.estatPagament = :estatPagament
            """)
    Long totalRecaudacioPerCampanyaIEstat(@Param("campanyaId") Long campanyaId, @Param("estatPagament") EstatPagament estatPagament);

}
