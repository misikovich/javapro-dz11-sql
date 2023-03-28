package org.example;

import org.example.backFrontEnd.AvailableCommands;
import org.example.backFrontEnd.ConsoleReader;
import org.example.backFrontEnd.KeyValuePair;
import org.example.model.Question;
import org.example.repository.QuestionRepositoryImpl;
import org.example.service.QuestionService;

import java.util.ArrayList;
import java.util.List;

public class Main {
    static final String exitCode = "q:";
    static final String programName = "Random Question by Topic Loader 3000";
    static  List<String> commands = new ArrayList<>(List.of("GETRAND", "ADD", "DEL"));
    public static void main(String[] args) {
        ConsoleReader consoleReader = new ConsoleReader();
        QuestionService questionService = new QuestionService(new QuestionRepositoryImpl());

        System.out.println(programName);
        System.out.println("Available commands:  " + String.join(", ", commands));
        for (KeyValuePair keyValuePair; !(keyValuePair = consoleReader.parseInput("Select your command: ")).key().equals(exitCode); System.out.printf("Type '%s' to quit\n", exitCode),
                System.out.println("Available commands:  " + String.join(", ", commands))) {
            if (keyValuePair.key().equals(AvailableCommands.GETRAND.toString())) {
                Question question = questionService.getRandomByTopic(keyValuePair.value());
                System.out.println(question.getText());
            } else if (keyValuePair.key().equals(AvailableCommands.ADD.toString())) {
                System.out.println(questionService.addQuestion(Question.buildFromString(keyValuePair.value())) ? "SUCCESS" : "FAILURE");
            } else if (keyValuePair.key().equals(AvailableCommands.DEL.toString())) {
                int id;
                try {
                     id = Integer.parseInt(keyValuePair.value());
                } catch (NumberFormatException e) {
                    System.out.printf("FAILURE: Incorrect value '%s'\n", keyValuePair.value());
                    continue;
                }
                System.out.println(questionService.deleteQuestion(id) ? "SUCCESS" : "FAILURE");
            } else {
                System.out.printf("FAILURE: Incorrect request '%s'\n", keyValuePair.key());
            }

        }
    }
}