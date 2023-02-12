package models;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "Quiz")
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String Title;


    @OneToMany(mappedBy = "quiz")
    private List<Question> questions;

    public Quiz() {
        questions = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "id:"+ id +" , Title :" + Title;
    }

}
