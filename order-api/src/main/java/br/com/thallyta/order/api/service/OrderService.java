package br.com.thallyta.order.api.service;

import br.com.thallyta.order.api.model.Order;
import br.com.thallyta.order.api.model.enums.Status;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final Logger logger = LoggerFactory.getLogger(OrderService.class);
    private final RabbitTemplate rabbitTemplate;

    @Value("${rabbitmq.exchange.name}")
    private String exchangeName;

    public OrderService(RabbitTemplate rabbitTemplate){
        this.rabbitTemplate = rabbitTemplate;
    }

    public Order queueOrder(Order order) {
        order.setStatus(Status.EM_PROCESSAMENTO);
        rabbitTemplate.convertAndSend(exchangeName, "", order);
        logger.info("Pedido enfileirado: {}", order.toString());
        return order;
    }
}
