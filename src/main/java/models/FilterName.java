package models;


import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "tbl_filterName")
public class FilterName {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 255,nullable = false)
    private String name;


    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date created;

    @OneToMany(mappedBy = "filterName")
    private List<Filter> filters;

    private boolean isDeleted;
}
