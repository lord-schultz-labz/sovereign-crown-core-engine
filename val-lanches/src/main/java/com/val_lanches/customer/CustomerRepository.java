package com.val_lanches.customer;

import org.springframework.data.jpa.repository.JpaRepository;

// Interface que permite acesso automático ao banco (CRUD pronto)
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
