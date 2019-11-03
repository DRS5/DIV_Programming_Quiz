package org.academiadecodigo.splicegirls36.project.terminal;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.string.StringSetInputScanner;

import java.util.HashSet;
import java.util.Set;
import java.util.Timer;


public class TerminalPrompt {

    private final Prompt prompt = new Prompt(System.in, System.out);
    private Set<String> possibleAnswers = new HashSet<>();
    private Timer timer = new Timer();


    public String askQuestion(String question){
        setAnswerOptions();

        StringSetInputScanner askQuestion = new StringSetInputScanner(possibleAnswers);
        askQuestion.setMessage(question);


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
