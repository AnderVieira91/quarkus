package br.com.touchtec;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.junit.jupiter.api.Test;

import br.com.touchtec.entityDefault.MyEntity;

import io.quarkus.test.TestTransaction;
import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.h2.H2DatabaseTestResource;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;


@TestTransaction
@QuarkusTest
@QuarkusTestResource(H2DatabaseTestResource.class)
public class ServiceTest {

    @Inject
    private Service service;

    @Test
    public void salvar() {
        MyEntity myEntity = this.service.save("Meu Teste");
        assertNotNull(myEntity);
        assertNotNull(myEntity.id);
        assertEquals("Meu Teste", myEntity.field);
    }

    @Test
    public void todos() {
        this.service.save("Primero");
        this.service.save("Segundo");
        List<MyEntity> entityList = this.service.all();
        assertTrue(CollectionUtils.isNotEmpty(entityList));
    }

}
