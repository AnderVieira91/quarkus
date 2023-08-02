package br.com.touchtec.camel;


import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import jakarta.enterprise.context.ApplicationScoped;


@ApplicationScoped
public class ExceptionHandler implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {
        System.out.println("ERRO CAMEL");
    }

}
