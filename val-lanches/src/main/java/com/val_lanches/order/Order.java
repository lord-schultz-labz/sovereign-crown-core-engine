package com.val_lanches.order;

import com.val_lanches.product.Product;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer numberOfItems;

    private Double totalPrice;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @ManyToMany
    @JoinTable(
        name = "order_products", // nome da tabela intermediária
        joinColumns = @JoinColumn(name = "order_id"), // coluna do pedido
        inverseJoinColumns = @JoinColumn(name = "product_id") // coluna do produto
    )
    private List<Product> products;

    public Order() {}

    // construtor
    public Order(Integer numberOfItems, Double totalPrice, OrderStatus status, List<Product> products) {
        this.numberOfItems = numberOfItems;
        this.totalPrice = totalPrice;
        this.status = status;
        this.products = products;
    }

    // GETTERS E SETTERS

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNumberOfItems() {
        return numberOfItems;
    }

    public void setNumberOfItems(Integer numberOfItems) {
        this.numberOfItems = numberOfItems;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
