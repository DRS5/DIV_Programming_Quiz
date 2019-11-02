package org.academiadecodigo.splicegirls36.project.terminal;

import org.academiadecodigo.splicegirls36.project.server.QuestionChooser;
import org.academiadecodigo.splicegirls36.project.server.RandomQuestionChooser;



public class TerminalTest {

    public static void main(String[] args) {
        QuestionChooser questionChooser = new RandomQuestionChooser();

        TerminalPrompt prompt = new TerminalPrompt(questionChooser);


        prompt.askQuestion();
        prompt.askQuestion();


    }
}
