package org.academiadecodigo.splicegirls36.project.terminal;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.string.StringSetInputScanner;

import java.util.HashSet;
import java.util.Set;

public class TerminalPrompt {

    Prompt prompt = new Prompt(System.in, System.out);   //Change output??

    Set<String> answers = new HashSet<>();


    public String askQuestion(){
        setAnswerOptions();

        StringSetInputScanner askQuestion = new StringSetInputScanner(answers);
        askQuestion.setMessage(Strings.QUESTION_1);

        String answer = prompt.getUserInput(askQuestion);
        return answer;
    }


    private void setAnswerOptions(){
        answers.add("A");
        answers.add("B");
        answers.add("C");
        answers.add("D");

    }


}
