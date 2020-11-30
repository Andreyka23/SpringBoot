package ru.geekbrains.orders.service.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.geekbrains.dtos.ProductDto;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table(name = "order_items")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "product_id")
    private Long productId;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "price_per_product")
    private Float pricePerProduct;

    @Column(name = "price")
    private Float price;

    public OrderItem(ProductDto productDto) {
        this.productId = productDto.getId();
        this.quantity = 1;
        this.price = productDto.getPrice();
        this.pricePerProduct = productDto.getPrice();
    }
}
