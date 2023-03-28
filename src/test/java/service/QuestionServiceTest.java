package service;

import org.example.model.Question;
import org.example.repository.dao.QuestionRepository;
import org.example.service.QuestionService;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class QuestionServiceTest {
    private static final QuestionRepository localQuestionRepository = new QuestionRepository() {
        @Override
        public Question get(int id) {return null;}
        @Override
        public Integer save(Question question) {
            return null;
        }
        @Override
        public void update(Question question) {}
        @Override
        public Integer delete(int id) {
            return null;
        }
        @Override
        public List<Question> getByTopic(String topic) {
            List<Question> questions = new ArrayList<>();
            for (Question question : testQuestions) {
                if (question.getTopic().equals(topic)) questions.add(question);
            }
            return questions;
        }
        @Override
        public List<Question> getAll() {
            return new ArrayList<>(testQuestions);
        }
    };
    private static QuestionService questionService;
    static List<Question> testQuestions = new ArrayList<>();

    @BeforeClass
    public static void init() {
        questionService = new QuestionService(localQuestionRepository);
        testQuestions.add(Question.builder().id(11).text("testText").topic("testTopic").build());
        testQuestions.add(Question.builder().id(12).text("testText1").topic("testTopic").build());
        testQuestions.add(Question.builder().id(13).text("testText1").topic("testTopic2").build());
        testQuestions.add(Question.builder().id(14).text("testText2").topic("testTopic").build());
    }
    @Test
    public void getRandomByTopicTest() {
        Question randomQuestionByTopic = questionService.getRandomByTopic("testTopic");
        System.out.println(randomQuestionByTopic);

        Assert.assertTrue(testQuestions.contains(randomQuestionByTopic));
    }
    @Test
    public void getRandomQuestionTest() {
        Question randomQuestion = questionService.getRandomQuestion();
        System.out.println(randomQuestion);

        Assert.assertTrue(testQuestions.contains(randomQuestion));
    }
}
