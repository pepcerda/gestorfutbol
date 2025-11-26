package com.jcerdar.gestorfutbol.persistence.dao;

import com.jcerdar.gestorfutbol.apirest.v1.model.Filtre;
import com.jcerdar.gestorfutbol.apirest.v1.model.ValorFiltre;
import com.jcerdar.gestorfutbol.persistence.model.CaixaFixa;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class CaixaFixaCustomDaoImpl implements CaixaFixaCustomDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Page<CaixaFixa> buscarConFiltros(Filtre filtre) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();

        // MAIN QUERY
        CriteriaQuery<CaixaFixa> query = cb.createQuery(CaixaFixa.class);
        Root<CaixaFixa> root = query.from(CaixaFixa.class);
        List<Predicate> predicates = construirPredicats(filtre, root, cb);
        query.where(predicates.toArray(new Predicate[0]));

        // ORDENACIÓN
        if (filtre.getSortField() != null && !filtre.getSortField().isEmpty()) {
            Path<?> sortPath = filtre.getSortField().contains(".")
                    ? root.get(filtre.getSortField().split("\\.")[0]).get(filtre.getSortField().split("\\.")[1])
                    : root.get(filtre.getSortField());

            query.orderBy(filtre.getSortOrder() != null && filtre.getSortOrder() == 1
                    ? cb.asc(sortPath)
                    : cb.desc(sortPath));
        } else {
            // Ordenación por defecto: campo 'id' ascendente
            query.orderBy(cb.asc(root.get("id")));
        }

        TypedQuery<CaixaFixa> typedQuery = entityManager.createQuery(query);
        int pageNum = filtre.getPageNum();
        typedQuery.setFirstResult(pageNum * filtre.getPageSize());
        typedQuery.setMaxResults(filtre.getPageSize());
        List<CaixaFixa> resultList = typedQuery.getResultList();

        // COUNT QUERY
        CriteriaQuery<Long> countQuery = cb.createQuery(Long.class);
        Root<CaixaFixa> countRoot = countQuery.from(CaixaFixa.class);
        List<Predicate> countPredicates = construirPredicats(filtre, countRoot, cb);
        countQuery.select(cb.count(countRoot)).where(countPredicates.toArray(new Predicate[0]));
        Long total = entityManager.createQuery(countQuery).getSingleResult();

        return new PageImpl<>(resultList, PageRequest.of(pageNum, filtre.getPageSize()), total);
    }

    @Override
    public List<CaixaFixa> buscarConFiltrosAll(Filtre filtre) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();

        // MAIN QUERY
        CriteriaQuery<CaixaFixa> query = cb.createQuery(CaixaFixa.class);
        Root<CaixaFixa> root = query.from(CaixaFixa.class);
        List<Predicate> predicates = construirPredicats(filtre, root, cb);
        query.where(predicates.toArray(new Predicate[0]));

        // ORDENACIÓN
        if (filtre.getSortField() != null && !filtre.getSortField().isEmpty()) {
            Path<?> sortPath = filtre.getSortField().contains(".")
                    ? root.get(filtre.getSortField().split("\\.")[0]).get(filtre.getSortField().split("\\.")[1])
                    : root.get(filtre.getSortField());

            query.orderBy(filtre.getSortOrder() != null && filtre.getSortOrder() == 1
                    ? cb.asc(sortPath)
                    : cb.desc(sortPath));
        } else {
            // Ordenación por defecto: campo 'id' ascendente
            query.orderBy(cb.asc(root.get("id")));
        }

        TypedQuery<CaixaFixa> typedQuery = entityManager.createQuery(query);
        int pageNum = filtre.getPageNum();
        typedQuery.setFirstResult(pageNum * filtre.getPageSize());
        List<CaixaFixa> resultList = typedQuery.getResultList();

        return resultList;
    }

    private List<Predicate> construirPredicats(Filtre filtre, Root<CaixaFixa> root, CriteriaBuilder cb) {
        List<Predicate> predicates = new ArrayList<>();

        if (filtre.getCampanyaActiva() != null) {
            predicates.add(cb.equal(root.get("campanya").get("id"), filtre.getCampanyaActiva()));
        }

        if (filtre.getFilters() != null) {
            for (Map.Entry<String, ValorFiltre> entry : filtre.getFilters().entrySet()) {
                String field = entry.getKey();
                ValorFiltre filter = entry.getValue();
                String value = filter.getValue();
                String matchMode = filter.getMatchMode();

                if (value != null && !value.isEmpty()) {
                    if ("nomComplet".equals(field)) {
                        // Concatenar nombre + apellido1 + apellido2
                        Expression<String> nomComplet = cb.concat(
                                cb.concat(cb.lower(root.get("nom")), " "),
                                cb.concat(cb.lower(root.get("llinatge1")), " "));
                        nomComplet = cb.concat(nomComplet, cb.lower(root.get("llinatge2")));

                        switch (matchMode) {
                            case "contains" -> predicates.add(cb.like(nomComplet, "%" + value.toLowerCase() + "%"));
                            case "startsWith" -> predicates.add(cb.like(nomComplet, value.toLowerCase() + "%"));
                            case "endsWith" -> predicates.add(cb.like(nomComplet, "%" + value.toLowerCase()));
                            case "equals" -> predicates.add(cb.equal(nomComplet, value.toLowerCase()));
                        }
                    } else {
                        Path<String> path;
                        if (field.contains(".")) {
                            String[] parts = field.split("\\.");
                            path = root.get(parts[0]).get(parts[1]);
                        } else {
                            path = root.get(field);
                        }

                        switch (matchMode) {
                            case "contains" -> predicates.add(cb.like(cb.lower(path), "%" + value.toLowerCase() + "%"));
                            case "equals" -> predicates.add(cb.equal(path, value));
                            case "startsWith" -> predicates.add(cb.like(cb.lower(path), value.toLowerCase() + "%"));
                            case "endsWith" -> predicates.add(cb.like(cb.lower(path), "%" + value.toLowerCase()));
                        }
                    }
                }
            }
        }

        return predicates;
    }
}
