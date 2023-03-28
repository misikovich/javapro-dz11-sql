package org.example.repository.dao;

import org.example.model.Question;

import java.util.List;

public interface QuestionRepository {
    Question get(int id);
    Integer save(Question question);
    void update(Question question);
    Integer delete(int id);
    List<Question> getByTopic(String topic);
    List<Question> getAll();
    Question getRandomByTopic(String topic);
}
