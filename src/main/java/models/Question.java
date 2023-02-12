package models;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "Question")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String Title;

    @OneToMany(mappedBy = "question")
    private List<Answer> answers;

    @ManyToOne
    private Quiz quiz;

    public Question() {
        answers = new ArrayList<>();
    }

    @Override
    public String toString(){
        StringBuilder builder = new StringBuilder();
        builder.append(this.Title);
        builder.append("\n");

        int i=1;
        for(Answer answer:answers)
            builder.append(i + ". " + answer.getText()+"\n");

        return builder.toString();
//        String str = "";
//        str += this.Title +'\n';
//        return str;
    }
}
