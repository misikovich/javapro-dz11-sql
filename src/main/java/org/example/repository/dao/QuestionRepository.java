package org.example.repository.dao;

import org.example.model.Question;

import java.util.List;

public interface QuestionRepository {
    Question get(int id);
    void save(Question question);
    void update(Question question);
    void delete(int id);
    List<Question> getByTopic(String topic);
}
