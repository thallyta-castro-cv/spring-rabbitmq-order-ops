package br.com.thallyta.order.notification.notification.service.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderItem {

    private UUID id = UUID.randomUUID();
    private Product product;
    private Integer amount;
}
