package br.com.touchtec.camel;


import static org.apache.camel.ExchangePattern.InOut;

import java.util.Map;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.support.DefaultExchange;
import org.apache.commons.collections4.MapUtils;

import jakarta.annotation.Nullable;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;


@ApplicationScoped
public class ExchangeSender {

    @Inject
    private CamelContext camelContext;

    private ProducerTemplate producerTemplate;

    @PostConstruct
    public void onPostConstruct() {
        this.producerTemplate = this.camelContext.createProducerTemplate();
    }

    public Exchange send(String endpointURI, Exchange exchange) {
        return this.send(endpointURI, exchange, null);
    }

    public Exchange send(String endpointURI, Exchange exchange, @Nullable Map<String, Object> paramExchange) {
        if (MapUtils.isNotEmpty(paramExchange)) {
            paramExchange.forEach(exchange::setProperty);
        }

        return this.producerTemplate.send(endpointURI, exchange);
    }

    public Exchange createExchange(Object body) {
        Exchange exchange = new DefaultExchange(this.camelContext, InOut);
        Message message = exchange.getIn();
        message.setHeader("simple", "true");
        message.setBody(body);
        return exchange;
    }

}
