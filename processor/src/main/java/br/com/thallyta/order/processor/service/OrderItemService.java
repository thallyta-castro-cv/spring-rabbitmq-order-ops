package br.com.thallyta.order.processor.service;

import br.com.thallyta.order.processor.model.Order;
import br.com.thallyta.order.processor.model.OrderItem;
import br.com.thallyta.order.processor.repository.OrderItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderItemService {

    private final OrderItemRepository orderItemRepository;

    public OrderItemService(OrderItemRepository orderItemRepository) {
        this.orderItemRepository = orderItemRepository;
    }

    public List<OrderItem> save(List<OrderItem> items) {
        return orderItemRepository.saveAll(items);
    }

    public void save(OrderItem item) {
        orderItemRepository.save(item);
    }

    public void updatedOrderItem(List<OrderItem> orderItems, Order order) {
        orderItems.forEach(item -> {
            item.setOrder(order);
            this.save(item);
        });
    }
}
