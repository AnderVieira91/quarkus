package br.com.touchtec.keycloak.controller;


import jakarta.ws.rs.QueryParam;


public class TabelaParams {
    @QueryParam("pagina")
    private Integer pagina;

    @QueryParam("qntPorPagina")
    private Integer qntPorPagina;

    public Integer getPagina() {
        return pagina;
    }

    public void setPagina(Integer pagina) {
        this.pagina = pagina;
    }

    public Integer getQntPorPagina() {
        return qntPorPagina;
    }

    public void setQntPorPagina(Integer qntPorPagina) {
        this.qntPorPagina = qntPorPagina;
    }
}
