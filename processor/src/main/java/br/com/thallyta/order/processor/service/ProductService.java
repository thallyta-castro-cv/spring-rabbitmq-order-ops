package br.com.thallyta.order.processor.service;

import br.com.thallyta.order.processor.model.OrderItem;
import br.com.thallyta.order.processor.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void save(List<OrderItem> items) {
        items.forEach(item ->
                productRepository.save(item.getProduct()));
    }
}
