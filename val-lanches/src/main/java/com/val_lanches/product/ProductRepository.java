package com.val_lanches.product;

import org.springframework.data.jpa.repository.JpaRepository;

// Interface que permite acesso automático ao banco (CRUD pronto)
public interface ProductRepository extends JpaRepository<Product, Long> {
}
