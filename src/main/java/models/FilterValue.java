package models;


import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "tbl_filterValue")
public class FilterValue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 255,nullable = false)
    private String name;

    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date created;

    @OneToMany(mappedBy = "filterValue")
    private List<Filter> filters;

    private boolean isDeleted;
}
