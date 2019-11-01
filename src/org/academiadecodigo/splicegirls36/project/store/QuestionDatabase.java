package org.academiadecodigo.splicegirls36.project.store;

import org.academiadecodigo.splicegirls36.project.domain.Answer;
import org.academiadecodigo.splicegirls36.project.domain.Question;

import java.util.Map;

public class QuestionDatabase implements Validatable {

    private Map<Question, Answer> qAMapping;

    @Override
    public Answer getRightAnswer(Question question) {

        return qAMapping.get(question);

    }
}
