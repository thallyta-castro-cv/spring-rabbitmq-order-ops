package br.com.thallyta.order.processor.repository;

import br.com.thallyta.order.processor.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID  > {
}
