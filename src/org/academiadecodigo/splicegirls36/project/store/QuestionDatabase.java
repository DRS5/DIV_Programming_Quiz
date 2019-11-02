package org.academiadecodigo.splicegirls36.project.store;

import org.academiadecodigo.splicegirls36.project.domain.Question;
import org.academiadecodigo.splicegirls36.project.terminal.Strings;
import java.util.LinkedList;
import java.util.List;

public class QuestionDatabase implements Validatable {

    private List<Question> qAList;


    public void buildList(){
        this.qAList = new LinkedList<>();
        qAList.add(new Question(Strings.QUESTION_1, "A"));
        /** qAList.add(new Question(Strings.QUESTION_2, "B"));
        qAList.add(new Question(Strings.QUESTION_3, "C"));
        qAList.add(new Question(Strings.QUESTION_4, "D")); */

    }



    @Override
    public String getRightAnswer(Question question) {
        return question.getCorrectAnswer();
    }

    public List<Question> getqAList() {
        return qAList;
    }

}
