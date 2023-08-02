package br.com.touchtec;


import java.util.List;

import br.com.touchtec.entityDefault.MyEntity;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;


@ApplicationScoped
@Transactional
public class Service {

    @Inject
    private EntityManager entityManager;

    public MyEntity save(String field) {
        MyEntity my = new MyEntity();
        my.field = field;
        this.entityManager.persist(my);
        return my;
    }

    public List<MyEntity> all() {
        return this.entityManager.createQuery("from MyEntity", MyEntity.class).getResultList();
    }
}
