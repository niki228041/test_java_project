package models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "Answer")
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String text;

    @Column(name = "is_true")
    private boolean isTrue;

    @ManyToOne
    @JoinColumn(name="question_id",nullable = false)
    private Question question;
}
