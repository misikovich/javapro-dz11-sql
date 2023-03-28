package repository;

import org.example.model.Question;
import org.example.repository.QuestionRepositoryImpl;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;


public class QuestionRepositoryImplTest {
    private static QuestionRepositoryImpl questionRepository;

    @BeforeClass
    public static void init() {
        questionRepository = new QuestionRepositoryImpl();
    }
    @Test
    public void getTest() {
        Question question = questionRepository.get(1);
        Assert.assertFalse(question.isEmpty());
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

        Question actual = questionRepository.get(question.getId());
        Assert.assertTrue(actual.isEmpty());
    }


}
