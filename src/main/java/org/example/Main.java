package org.example;

import models.*;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibemateSessionUnils;

import java.security.SecureRandom;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");


        //RoleOperation roleOperation = new RoleOperation();
        //roleOperation.crudOper();


        Scanner in = new Scanner(System.in);
        String continue_or_no="";

        do{
            System.out.println("[1] - pass the test");
            System.out.println("[2] - create your own test");
            System.out.println("[0] - exit");

            continue_or_no = in.nextLine();

            switch (continue_or_no)
            {
                case "1":takeAQuiz();break;
                case "2":createQuiz();break;
                case "0":System.out.println("See you later!");;break;
                default: System.out.println("Wrong answer!"); ;break;
            }
        }while (!continue_or_no.equals("0"));

    }

    private static void createQuiz()
    {
        System.out.println("Enter Quiz Title");
        Scanner in = new Scanner(System.in);
        String quizTitle = in.nextLine();
        Quiz quiz = new Quiz();
        quiz.setTitle(quizTitle);

        try(Session context = HibemateSessionUnils.getSessionFactory().openSession())
        {
            Transaction tx = context.beginTransaction();
            context.save(quiz);

            tx.commit();
            context.close();
        }


        System.out.println("Create Question");
        String continue_or_no="";

        do {
            addQuestionWithAnswers(quiz);

            System.out.println("[1] - to create Question");
            System.out.println("[0] - to exit");
            continue_or_no = in.nextLine();
        }while (!continue_or_no.equals("0"));
    }

    private static void takeAQuiz()
    {
        System.out.println("Select the Quiz");

        getQuizzes();

        Scanner in = new Scanner(System.in);
        System.out.println("Enter Quiz id to start the test");
        String quizId = in.nextLine();


        Session context = HibemateSessionUnils.getSessionFactory().openSession();
        Transaction transaction = context.beginTransaction();

        Quiz quiz = context.get(Quiz.class,Integer.parseInt(quizId));
        int correntAnswers = 0;

        System.out.println("______");
        for (var question:quiz.getQuestions())
        {
            System.out.println(question.getTitle());
            int i = 0;
            for (var answer:question.getAnswers())
            {
                i++;
                System.out.print("[" + i + "]");
                System.out.println(answer.getText());
            }

            System.out.println("Enter id of answer:");
            var answerId = in.nextLine();
            i = 0;
            for (var answer:question.getAnswers())
            {
                i++;
                if(i == Integer.parseInt(answerId))
                {
                    if(answer.isTrue())
                    {
                        correntAnswers++;
                    }
                }
            }

        }
        var rightAnswersCount = quiz.getQuestions().toArray().length;
        System.out.println("Corrent Answers:" + correntAnswers + "/" + rightAnswersCount);
        float noteIn12System = (float)(correntAnswers)/rightAnswersCount * 12;
        System.out.println("Your note is:" + noteIn12System);


        transaction.commit();
        context.close();


        System.out.println("Create Question");
    }

    private static List<Quiz> getQuizzes(){
        Session context = HibemateSessionUnils.getSessionFactory().openSession();
        Transaction transaction = context.beginTransaction();

        String hql = "FROM Quiz";
        Query query = context.createQuery(hql);
        List<Quiz> quizzes = query.list();

        quizzes.forEach(quiz -> System.out.println(quiz.toString()));

        transaction.commit();
        context.close();
        return quizzes;
    }



    private static void addQuestionWithAnswers(Quiz quiz){
        try(Session context = HibemateSessionUnils.getSessionFactory().openSession())
        {
            Scanner in = new Scanner(System.in);
            Transaction tx = context.beginTransaction();


            System.out.println("Enter a question");
            String questionText = in.nextLine();

            Question question = new Question();
            question.setTitle(questionText);
            question.setQuiz(quiz);
            context.save(question);

            String action = "";
            String answer_text = "";
            String isTrue = "";
            boolean isTrue_to_answer = false;

            do{
                System.out.println("Enter the answer:");

//                if(action == "0")
//                {
//                    tx.commit();
//                    break;
//                }
                System.out.println("Enter answer");
                answer_text = in.nextLine();

                System.out.println("1 - is true; 0 - is false");
                isTrue = in.nextLine();

                System.out.println("isTrue");
                System.out.println(isTrue);




                if(isTrue.equals("1")){
                    isTrue_to_answer = true;
                }
                else{
                    isTrue_to_answer = false;
                }

                System.out.println("isTrue_to_answer");
                System.out.println(isTrue_to_answer);

                Answer answer = new Answer();
                answer.setText(answer_text);
                answer.setTrue(isTrue_to_answer);
                answer.setQuestion(question);
                context.save(answer);

                System.out.println("0 - End the question");
                System.out.println("1 - New Answer");
                action = in.nextLine();
            }while (!action.equals("0"));

            tx.commit();


            context.close();
        }
    }

}