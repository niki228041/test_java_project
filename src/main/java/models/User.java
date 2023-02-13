package models;


import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "tbl_users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(length = 100,nullable = false)
    private String firstName;
    @Column(length = 100,nullable = false)
    private String lastName;
    @Column(length = 200,nullable = false)
    private String email;
    @Column(length = 30,nullable = false)
    private String phone;
    @Column(length = 200,nullable = false)
    private String password;

    @OneToMany(mappedBy = "user")
    private List<UserRole> userRoles;

    @OneToMany(mappedBy = "user")
    private List<Order> orders;

    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date created;


    private boolean isDeleted;

    public User(){
        userRoles = new ArrayList<>();
        orders = new ArrayList<>();
    }
}
