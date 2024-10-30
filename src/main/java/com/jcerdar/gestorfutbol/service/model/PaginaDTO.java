package com.jcerdar.gestorfutbol.service.model;

public class PaginaDTO<T> {

    private long total;

    private T result;

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}
