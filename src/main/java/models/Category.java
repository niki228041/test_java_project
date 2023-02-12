package models;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name="tbl_category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 200,nullable = false)
    private String name;

    @Column(length = 200)
    private String image;

    @OneToMany(mappedBy = "category")
    private List<Product> products;

    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date created;

    private boolean isDeleted;

    public Category() {
//        super();  // визиває базовий конструктор
        products = new ArrayList<>();
    }
}
