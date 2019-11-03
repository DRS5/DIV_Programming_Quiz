package org.academiadecodigo.splicegirls36.project.store;

import org.academiadecodigo.splicegirls36.project.domain.Question;
import org.academiadecodigo.splicegirls36.project.terminal.Strings;
import java.util.LinkedList;
import java.util.List;

public class QuestionDatabase implements Validatable {

    private List<Question> qAList;


    public void buildList(){
        this.qAList = new LinkedList<>();
        qAList.add(new Question(Strings.QUESTION_1, "B"));
        qAList.add(new Question(Strings.QUESTION_2, "A"));
        qAList.add(new Question(Strings.QUESTION_3, "D"));
        qAList.add(new Question(Strings.QUESTION_4, "B"));
        qAList.add(new Question(Strings.QUESTION_5, "A"));
        qAList.add(new Question(Strings.QUESTION_6, "C"));
        qAList.add(new Question(Strings.QUESTION_7, "D"));
        qAList.add(new Question(Strings.QUESTION_8, "A"));
        qAList.add(new Question(Strings.QUESTION_9, "C"));
        qAList.add(new Question(Strings.QUESTION_10, "B"));
        qAList.add(new Question(Strings.QUESTION_11, "C"));
        qAList.add(new Question(Strings.QUESTION_12, "A"));
        qAList.add(new Question(Strings.QUESTION_13, "A"));
        qAList.add(new Question(Strings.QUESTION_14, "C"));
        qAList.add(new Question(Strings.QUESTION_15, "D"));
        qAList.add(new Question(Strings.QUESTION_16, "C"));

    }



    @Override
    public String getRightAnswer(Question question) {
        return question.getCorrectAnswer();
    }

    public List<Question> getqAList() {
        return qAList;
    }

}
