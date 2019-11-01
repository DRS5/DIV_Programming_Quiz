package org.academiadecodigo.splicegirls36.project.store;

import org.academiadecodigo.splicegirls36.project.domain.Answer;
import org.academiadecodigo.splicegirls36.project.domain.Question;

public interface Validatable {

    public Answer getRightAnswer (Question question);

}
