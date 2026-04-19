package com.val_lanches.order;

import org.springframework.data.jpa.repository.JpaRepository;

// Interface que permite acesso automático ao banco (CRUD pronto)
public interface OrderRepository extends JpaRepository<Order, Long> {
}
