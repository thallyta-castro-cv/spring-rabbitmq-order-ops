package br.com.thallyta.order.api.controller;

import br.com.thallyta.order.api.controller.openapi.OrderControllerOpenApi;
import br.com.thallyta.order.api.model.Order;
import br.com.thallyta.order.api.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController implements OrderControllerOpenApi {

    private final Logger logger = LoggerFactory.getLogger(OrderController.class);

    private final OrderService service;

    public OrderController(OrderService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Order> create(@RequestBody Order order) {
        logger.info("Pedido recebido: {}", order.toString());
        return ResponseEntity.status(HttpStatus.CREATED).body(order);
    }
}
