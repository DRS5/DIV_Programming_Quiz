package org.academiadecodigo.splicegirls36.project.terminal;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.string.StringSetInputScanner;
import org.academiadecodigo.splicegirls36.project.server.QuestionChooser;
import org.academiadecodigo.splicegirls36.project.server.RandomQuestionChooser;
import java.util.HashSet;
import java.util.Set;


public class TerminalPrompt {

    private final Prompt prompt = new Prompt(System.in, System.out);   //Change output??
    private final QuestionChooser questionChooser;


    Set<String> possibleAnswers = new HashSet<>();

    public TerminalPrompt(QuestionChooser questionChooser){
        this.questionChooser = questionChooser;
    }


    public String askQuestion(){
        setAnswerOptions();

        StringSetInputScanner askQuestion = new StringSetInputScanner(possibleAnswers);
        askQuestion.setMessage(questionChooser.chooseQuestion().getText()); //logic for asking each question

        String answer = prompt.getUserInput(askQuestion);
        return answer;
    }


    private void setAnswerOptions(){
        possibleAnswers.add("A");
        possibleAnswers.add("B");
        possibleAnswers.add("C");
        possibleAnswers.add("D");
    }


}
