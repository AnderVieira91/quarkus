package br.com.touchtec;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.junit.jupiter.api.Test;

import br.com.touchtec.entityOther.Cliente;

import io.quarkus.test.TestTransaction;
import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.h2.H2DatabaseTestResource;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;


@TestTransaction
@QuarkusTest
@QuarkusTestResource(H2DatabaseTestResource.class)
public class OtherServiceTest {

    @Inject
    private OtherService otherService;

    @Test
    public void salvar() {
        Cliente cliente = new Cliente();
        cliente.setNome("Nome");
        cliente.setSobrenome("Sobrenome");
        Cliente clienteSalvo = this.otherService.insert(cliente);
        assertNotNull(clienteSalvo);
        assertNotNull(clienteSalvo.getId());
        assertEquals("Nome", clienteSalvo.getNome());
        assertEquals("Sobrenome", clienteSalvo.getSobrenome());
    }

    @Test
    public void todos() {
        Cliente cliente = new Cliente();
        cliente.setNome("Outro");
        cliente.setSobrenome("Cara");
        this.otherService.insert(cliente);

        Cliente cliente2 = new Cliente();
        cliente2.setNome("Mais");
        cliente2.setSobrenome("Um");
        this.otherService.insert(cliente2);

        List<Cliente> clienteList = this.otherService.others();
        assertTrue(CollectionUtils.isNotEmpty(clienteList));
    }

}
