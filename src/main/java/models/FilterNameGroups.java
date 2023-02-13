package models;

import lombok.Data;

import javax.persistence.*;


@Data
@Entity
@Table(name = "tbl_filterNameGroups")
@IdClass(FilterNameGroupsPK.class)
public class FilterNameGroups {

    @Id
    @ManyToOne
    @JoinColumn(name="filterName_id",nullable = false)
    private FilterName filterName;

    @Id
    @ManyToOne
    @JoinColumn(name="filterValue_id",nullable = false)
    private FilterValue filterValue;
}
