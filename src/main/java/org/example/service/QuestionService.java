package org.example.service;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.example.model.Question;
import org.example.repository.dao.QuestionRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

@RequiredArgsConstructor
public class QuestionService {
    @NonNull
    private final QuestionRepository questionRepository;
    private final Map<String, List<Question>> questionsByTopic = new HashMap<>();

    public Question getRandomByTopic(String topic) {
//        List<Question> questions = questionsByTopic.computeIfAbsent(topic, questionRepository::getByTopic);
//        questionsByTopic.put(topic, questions);
//        if (questions.isEmpty()) return Question.builder().build();
//        int randomInt = new Random().nextInt(questions.size());
//        return questions.get(randomInt);
        Question question = questionRepository.getRandomByTopic(topic);
        return question == null ? new Question() : question;
    }

    public Question getRandomQuestion() {
        List<Question> questions = questionRepository.getAll();
        Question randomQuestion = questions.get(new Random().nextInt(questions.size()));
        questions.clear();
        return randomQuestion;
    }

    public Boolean addQuestion(Question question) {
        Integer savedRows = questionRepository.save(question);
        return savedRows >= 1;
    }

    public Boolean deleteQuestion(int id) {
        Integer deletedRows = questionRepository.delete(id);
        return deletedRows >= 1;
    }
}
