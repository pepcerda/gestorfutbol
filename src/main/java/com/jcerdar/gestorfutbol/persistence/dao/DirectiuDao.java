package com.jcerdar.gestorfutbol.persistence.dao;

import com.jcerdar.gestorfutbol.persistence.model.Directiu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DirectiuDao extends JpaRepository<Directiu, Long> {

    Page<Directiu> findAllByDirectiva(Long directivaId, Pageable pageable);
}
