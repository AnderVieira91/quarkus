package br.com.touchtec.keycloak.controller;


import java.util.ArrayList;
import java.util.List;

import io.quarkus.security.Authenticated;
import jakarta.ws.rs.BeanParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;


@Path("/api")
@Produces(MediaType.APPLICATION_JSON)
@Authenticated
public class RoleController {

    @GET
    @Path("/tabela")
    public List<Linha> getLinhas(@BeanParam TabelaParams tabelaParam) {
        List<Linha> tabela = new ArrayList<>();

        for (int i = 1; i <= tabelaParam.getQntPorPagina(); i++) {
            Linha linha = new Linha();
            linha.pagina = tabelaParam.getPagina();
            linha.valor = tabelaParam.getPagina().toString() + "-" + i;
            linha.bool = i % 2 == 0;
            tabela.add(linha);
        }

        return tabela;
    }

    public class Linha {
        public Integer pagina;
        public String valor;
        public boolean bool;
    }

}
