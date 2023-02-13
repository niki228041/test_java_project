package models;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "tbl_productImage")
public class ProductImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 255,nullable = false)
    private String name;

    private int priority;


    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date created;

    @ManyToOne
    @JoinColumn(name="product_id",nullable = false)
    private Product product;

    private boolean isDeleted;
}
