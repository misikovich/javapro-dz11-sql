package repository;

import org.example.model.Question;
import org.example.repository.QuestionRepositoryImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class QuestionRepositoryImplTest {
    private final String username = "postgres";
    private final String password = "postgrespw";
    private final String url = "jdbc:postgresql://localhost:32768/postgres";
    private Connection connection;
    private QuestionRepositoryImpl questionRepository;

    @Before
    public void init() throws SQLException {
        connection = DriverManager.getConnection(url, username, password);
        questionRepository = new QuestionRepositoryImpl(connection);
    }
    @Test
    public void getTest() {
        Question question = questionRepository.get(1);
        Assert.assertNotNull(question);
    }
    @Test
    public void getByTopicTest() {
        questionRepository.getByTopic("Java core").forEach(System.out::println);
    }
    @Test
    public void saveTest() {
        Question question = Question.builder().text("Saved test").topic("Test").build();
        questionRepository.save(question);

        Question actualQuestion = questionRepository.getByTopic("Test").get(0);
        Assert.assertEquals(question.getText(), actualQuestion.getText());
        Assert.assertEquals(question.getTopic(), actualQuestion.getTopic());
    }
    @Test
    public void updateTest() {
        Question savedQuestion = questionRepository.getByTopic("Test").get(0);
        Question updatedQuestion = Question.builder().id(savedQuestion.getId()).text("Updated test").topic("Test").build();
        questionRepository.update(updatedQuestion);
        Question actualQuestion = questionRepository.getByTopic("Test").get(0);

        Assert.assertNotEquals(savedQuestion, actualQuestion);
    }

    @Test
    public void deleteTest() {
        Question question = questionRepository.getByTopic("Test").get(0);
        questionRepository.delete(question.getId());

        Assert.assertNull(questionRepository.get(question.getId()));
    }


}
