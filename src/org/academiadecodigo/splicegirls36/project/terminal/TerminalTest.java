package org.academiadecodigo.splicegirls36.project.terminal;

import org.academiadecodigo.splicegirls36.project.domain.Answer;
import org.academiadecodigo.splicegirls36.project.domain.Question;
import org.academiadecodigo.splicegirls36.project.store.QuestionDatabase;

import java.util.HashMap;
import java.util.Map;

public class TerminalTest {

    public static void main(String[] args) {

        TerminalPrompt prompt = new TerminalPrompt();

        prompt.askQuestion();
        prompt.askQuestion();


    }
}
