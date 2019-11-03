package org.academiadecodigo.splicegirls36.project.store;

import org.academiadecodigo.splicegirls36.project.domain.Question;
import org.academiadecodigo.splicegirls36.project.terminal.Strings;
import java.util.LinkedList;
import java.util.List;

public class QuestionDatabase implements Validatable {

    private List<String> qAList;


    public void buildList(){
        this.qAList = new LinkedList<>();
        qAList.add(Strings.QUESTION_1);
        qAList.add(Strings.QUESTION_2);
        qAList.add(Strings.QUESTION_3);
        qAList.add(Strings.QUESTION_4);
        qAList.add(Strings.QUESTION_5);
        qAList.add(Strings.QUESTION_6);
        qAList.add(Strings.QUESTION_7);
        qAList.add(Strings.QUESTION_8);
        qAList.add(Strings.QUESTION_9);
        qAList.add(Strings.QUESTION_10);
        qAList.add(Strings.QUESTION_11);
        qAList.add(Strings.QUESTION_12);
        qAList.add(Strings.QUESTION_13);
        qAList.add(Strings.QUESTION_14);
        qAList.add(Strings.QUESTION_15);
        qAList.add(Strings.QUESTION_16);

    }



    @Override
    public String getRightAnswer(Question question) {
        return question.getCorrectAnswer();
    }

    public List<String> getqAList() {
        return qAList;
    }

}
