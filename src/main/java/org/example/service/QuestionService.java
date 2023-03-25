package org.example.service;

import lombok.AllArgsConstructor;
import org.example.model.Question;
import org.example.repository.dao.QuestionRepository;

import java.util.List;
import java.util.Random;

@AllArgsConstructor
public class QuestionService {
    private QuestionRepository questionRepository;

    public Question getRandomByTopic(String topic) {
        List<Question> questions = questionRepository.getByTopic(topic);
        if (questions.isEmpty()) return Question.builder().build();
        int randomInt = new Random().nextInt(questions.size());
        return questions.get(randomInt);
    }
}
