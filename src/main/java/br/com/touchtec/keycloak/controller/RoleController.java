package br.com.touchtec.keycloak.controller;


import org.jboss.resteasy.reactive.NoCache;

import io.quarkus.security.Authenticated;
import io.quarkus.security.identity.SecurityIdentity;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;


@Path("/keycloak")
@Authenticated
@NoCache
public class RoleController {

    @Inject
    private SecurityIdentity securityIdentity;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public User getUsername() {
        User user = new User();
        user.name = this.securityIdentity.getPrincipal().getName();
        user.email = "teste@teste.com";
        return user;
    }


    public class User {
        public String name;
        public String email;
    }

}
