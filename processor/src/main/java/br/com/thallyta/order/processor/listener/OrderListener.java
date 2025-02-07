package br.com.thallyta.order.processor.listener;


import br.com.thallyta.order.processor.model.Order;
import br.com.thallyta.order.processor.model.enums.Status;
import br.com.thallyta.order.processor.service.OrderService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class OrderListener {

    private final OrderService orderService;

    public OrderListener(OrderService orderService) {
        this.orderService = orderService;
    }

    @RabbitListener(queues = "pedidos.v1.pedido-criado.gerar-processamento")
    public void createOrder(Order order){
        order.setStatus(Status.PROCESSADO);
        orderService.save(order);
    }

}
