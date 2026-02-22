package com.jcerdar.gestorfutbol.persistence.dao;

import com.jcerdar.gestorfutbol.persistence.model.CategoriaDespesa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaDespesaDao extends JpaRepository<CategoriaDespesa, Long>, CategoriaDespesaCustomDao {

    List<CategoriaDespesa> findAllByTenantId(Long tenantId);
}
