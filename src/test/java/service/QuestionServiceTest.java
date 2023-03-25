package service;

import org.example.repository.QuestionRepositoryImpl;
import org.example.service.QuestionService;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class QuestionServiceTest {
    private final String username = "postgres";
    private final String password = "postgrespw";
    private final String url = "jdbc:postgresql://localhost:32768/postgres";
    private Connection connection;

    @Before
    public void init() throws SQLException {
        connection = DriverManager.getConnection(url, username, password);
    }
    @Test
    public void getRandomByTopicTest() {
        QuestionService questionService = new QuestionService(new QuestionRepositoryImpl(connection));
        System.out.println(questionService.getRandomByTopic("Java core"));
    }
}
