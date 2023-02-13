package models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="tbl_roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(length = 255,nullable = false)
    private String name;

    public void setName(String name) {
        this.name = name;
    }


    @OneToMany(mappedBy = "role")
    private List<UserRole> userRoles;

    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date created;


    private boolean isDeleted;

    public Role(){
        userRoles = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "id:"+id+" , name:" + name;
    }
}
