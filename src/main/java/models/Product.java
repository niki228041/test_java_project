package models;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "tbl_product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 255,nullable = false)
    private String name;

    @Column(length = 4255,nullable = false)
    private String descriprion;


    private int price;

    @ManyToOne
    @JoinColumn(name="category_id",nullable = false)
    private Category category;

    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date created;

    private boolean isDeleted;
}
