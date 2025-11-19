package com.jcerdar.gestorfutbol.apirest.v1.model;

import java.util.List;
import java.util.Map;

public class Filtre {

    private int pageNum = 1;

    private int pageSize = 10;

    private String lang = "es";

    private String userId;

    private Long campanyaActiva;

    private Long categoriaActiva;

    private Long equipActiu;

    private String sortField;

    private Integer sortOrder;

    private Map<String, ValorFiltre> filters;

    private List<Long> ids;

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Long getCampanyaActiva() {
        return campanyaActiva;
    }

    public void setCampanyaActiva(Long campanyaActiva) {
        this.campanyaActiva = campanyaActiva;
    }

    public Long getCategoriaActiva() {
        return categoriaActiva;
    }

    public void setCategoriaActiva(Long categoriaActiva) {
        this.categoriaActiva = categoriaActiva;
    }

    public Long getEquipActiu() {
        return equipActiu;
    }

    public void setEquipActiu(Long equipActiu) {
        this.equipActiu = equipActiu;
    }

    public String getSortField() {
        return sortField;
    }

    public void setSortField(String sortField) {
        this.sortField = sortField;
    }

    public Integer getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }

    public Map<String, ValorFiltre> getFilters() {
        return filters;
    }

    public void setFilters(Map<String, ValorFiltre> filters) {
        this.filters = filters;
    }

    public List<Long> getIds() {
        return ids;
    }

    public void setIds(List<Long> ids) {
        this.ids = ids;
    }   
}
