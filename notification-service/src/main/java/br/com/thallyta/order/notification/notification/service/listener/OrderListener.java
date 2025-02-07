package br.com.thallyta.order.notification.notification.service.listener;

import br.com.thallyta.order.notification.notification.service.model.Order;
import br.com.thallyta.order.notification.notification.service.service.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class OrderListener {

    private final Logger logger = LoggerFactory.getLogger(OrderListener.class);
    private final EmailService emailService;

    public OrderListener(EmailService emailService) {
        this.emailService = emailService;
    }

    @RabbitListener(queues = "pedidos.v1.pedido-criado.gerar-notificacao")
    private void sendNotificationOrderReceive(Order order){
        emailService.sendEmail(order, "Pedido de compra", "realizado");
        logger.info("Notificação gerada de compra: {}", order.toString());
    }

    @RabbitListener(queues = "pedidos.v1.pedido-processado")
    private void sendNotificationOrderProcessed(Order order){
        emailService.sendEmail(order, "Pedido confirmado", "confirmado");
        logger.info("Notificação de pedido confirmado: {}", order.toString());
    }
}
