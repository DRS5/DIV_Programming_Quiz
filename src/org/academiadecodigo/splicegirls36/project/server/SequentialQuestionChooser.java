package org.academiadecodigo.splicegirls36.project.server;

import org.academiadecodigo.splicegirls36.project.domain.Question;
import org.academiadecodigo.splicegirls36.project.store.QuestionDatabase;

import java.util.List;


public class SequentialQuestionChooser implements QuestionChooser{

    private List<Question> questions;
    private  QuestionDatabase questionDatabase;
    private int questionIndex = 0;

    public SequentialQuestionChooser(){
        questionDatabase = new QuestionDatabase();
        questionDatabase.buildList();
        questions = questionDatabase.getqAList();
    }



    @Override
    public Question chooseQuestion() {
       Question question = questions.get(questionIndex);
       questionIndex++;
       return question;
    }

}
