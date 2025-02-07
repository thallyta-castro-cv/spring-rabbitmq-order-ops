package br.com.thallyta.order.processor.service;

import br.com.thallyta.order.processor.model.Order;
import br.com.thallyta.order.processor.model.OrderItem;
import br.com.thallyta.order.processor.repository.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Value("${rabbitmq.exchange.name}")
    private String exchangeName;

    private final Logger logger = LoggerFactory.getLogger(OrderService.class);
    private final OrderRepository orderRepository;
    private final ProductService productService;
    private final OrderItemService orderItemService;
    private final RabbitTemplate rabbitTemplate;

    public OrderService(OrderRepository orderRepository, ProductService productService, OrderItemService orderItemService, RabbitTemplate rabbitTemplate) {
        this.orderRepository = orderRepository;
        this.productService = productService;
        this.orderItemService = orderItemService;
        this.rabbitTemplate = rabbitTemplate;
    }

    public void save(Order order){
        productService.save(order.getItems());
        List<OrderItem> orderItems = orderItemService.save(order.getItems());
        orderRepository.save(order);
        orderItemService.updatedOrderItem(orderItems, order);
        rabbitTemplate.convertAndSend(exchangeName, "", order);
        logger.info("Pedido salvo: {}", order.toString());
    }
}
