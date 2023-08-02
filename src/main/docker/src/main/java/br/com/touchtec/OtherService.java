package br.com.touchtec;


import java.util.List;

import br.com.touchtec.entityOther.Cliente;

import io.quarkus.cache.CacheInvalidateAll;
import io.quarkus.cache.CacheResult;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;


@Transactional
@ApplicationScoped
public class OtherService {

    private static final String ALL_CLIENTES_CACHE = "allClientesCache";

    @Inject
    @PersistenceContext(unitName = "other")
    private EntityManager entityManager;

    @CacheResult(cacheName = ALL_CLIENTES_CACHE)
    public List<Cliente> others() {
        return this.entityManager.createQuery("from Cliente", Cliente.class).getResultList();
    }

    @CacheInvalidateAll(cacheName = ALL_CLIENTES_CACHE)
    public Cliente insert(Cliente cliente) {
        this.entityManager.persist(cliente);
        return cliente;
    }

}
