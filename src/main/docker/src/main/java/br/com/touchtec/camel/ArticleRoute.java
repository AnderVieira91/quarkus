package br.com.touchtec.camel;


import org.apache.camel.builder.endpoint.EndpointRouteBuilder;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;


@ApplicationScoped
public class ArticleRoute extends EndpointRouteBuilder {

    @Inject
    private ArticleProcessor articleProcessor;

    @Inject
    private ExceptionHandler exceptionHandler;

    @Override
    public void configure() throws Exception {
        // Rota de artigos do coletor kanban do ceadis
        from("direct:articleRoute")
                .doTry()
                    .to("http://192.168.5.125:28080/ceadis/dyn-mvc/entity/articles?clientCod=36037")
                    .process(this.articleProcessor)
                .doCatch(Exception.class)
                    .process(this.exceptionHandler);
    }

}
