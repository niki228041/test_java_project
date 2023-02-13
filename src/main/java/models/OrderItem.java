package models;

import lombok.Data;

import javax.persistence.*;


@Data
@Entity
@Table(name = "tbl_orderItem")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name="product_id",nullable = false)
    private Product product;

    @ManyToOne
    @JoinColumn(name="order_id",nullable = false)
    private Order order;

    private int priceBuy;
    private int count;


    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date created;

    private boolean isDeleted;
}
