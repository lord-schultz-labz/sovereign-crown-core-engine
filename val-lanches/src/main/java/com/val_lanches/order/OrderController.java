package com.val_lanches.order;

import com.val_lanches.product.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // define que isso é um controller REST
@RequestMapping("/orders") // rota base: /orders
@CrossOrigin // permite acesso do frontend (HTML/JS)
public class OrderController {

    @Autowired
    // injeta automaticamente o repository (Spring faz isso sozinho)
    private OrderRepository repository;

    // =========================
    // LISTAR TODOS OS PEDIDOS
    // =========================
    @GetMapping
    public List<Order> findAll() {
        return repository.findAll();
    }

    // =========================
    // CRIAR NOVO PEDIDO
    // =========================
    @PostMapping
    public Order create(@RequestBody Order order) {

        // define status padrão ao criar
        order.setStatus(OrderStatus.IN_PROGRESS);

        /*
         * Aqui estamos fazendo uma regra de negócio simples:
         * - contar quantos produtos existem
         * - somar o preço de todos eles
         */
        if (order.getProducts() != null) {

            // quantidade de itens = tamanho da lista
            order.setNumberOfItems(order.getProducts().size());

            // soma todos os preços dos produtos
            double total = 0;

            for (Product p : order.getProducts()) {
                if (p.getPrice() != null) {
                    total += p.getPrice();
                }
            }

            order.setTotalPrice(total);
        }

        // salva no banco
        return repository.save(order);
    }

    // =========================
    // ATUALIZAR PEDIDO
    // =========================
    @PutMapping("/{id}")
    public Order updateStatus(@PathVariable Long id, @RequestBody Order partial) {
    Order order = repository.findById(id).orElseThrow();

    order.setStatus(partial.getStatus());

    return repository.save(order);
    }

    // =========================
    // DELETAR PEDIDO
    // =========================
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
