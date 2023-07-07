package br.com.touchtec;


import java.util.List;

import org.apache.camel.Exchange;

import br.com.touchtec.camel.ExchangeSender;
import br.com.touchtec.entityDefault.MyEntity;
import br.com.touchtec.entityOther.Cliente;

import io.quarkus.cache.CacheInvalidateAll;
import io.quarkus.cache.CacheResult;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;


@Path("/my")
public class MyController {

    @Inject
    private Service service;

    @Inject
    private OtherService otherService;

    @Inject
    private ExchangeSender exchangeSender;

    @GET
    @CacheResult(cacheName = "helloWorld")
    public String hello() {
        return "Hello JAKARTA";
    }

    @GET
    @Path("/bye")
    @Produces(MediaType.APPLICATION_JSON)
    @CacheInvalidateAll(cacheName = "helloWorld")
    public String goodBye() {
        return "Good Bey JAKARTA";
    }

    @GET
    @Path("/insert")
    @Produces(MediaType.APPLICATION_JSON)
    public MyEntity insert(@QueryParam("field") String field) {
        return this.service.save(field);
    }

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public List<MyEntity> all() {
        return this.service.all();
    }

    @GET
    @Path("/others")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Cliente> others() {
        return this.otherService.others();
    }

    @POST
    @Path("/cliente")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Cliente insertCliente(Cliente cliente) {
        return this.otherService.insert(cliente);
    }

    @GET
    @Path("/article")
    @Produces(MediaType.TEXT_PLAIN)
    public String getArticle() {
        Exchange exchange = this.exchangeSender.createExchange(null);
        Exchange response = this.exchangeSender.send("direct:articleRoute", exchange);

        if (response.getProperty(Exchange.EXCEPTION_CAUGHT, Exception.class) != null) {
            return "ERROR CAMEL";
        }

        return response.getIn().getBody(String.class);
    }

}
