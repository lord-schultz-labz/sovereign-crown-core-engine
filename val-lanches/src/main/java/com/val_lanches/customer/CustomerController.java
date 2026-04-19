package com.val_lanches.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
@CrossOrigin
public class CustomerController {

    @Autowired
    private CustomerRepository repository;

    // LISTAR TODOS
    @GetMapping
    public List<Customer> findAll() {
        return repository.findAll();
    }

    // CRIAR
    @PostMapping
    public Customer create(@RequestBody Customer customer) {
        return repository.save(customer);
    }

    // ATUALIZAR
    @PutMapping("/{id}")
    public Customer update(@PathVariable Long id, @RequestBody Customer customer) {
        customer.setId(id);
        return repository.save(customer);
    }

    // DELETAR
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
