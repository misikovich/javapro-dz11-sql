package org.example.repository;

import org.example.exceptions.SQLUpdateException;
import org.example.model.Question;
import org.example.repository.dao.QuestionRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QuestionRepositoryImpl implements QuestionRepository {
    private final Connection connection;

    public QuestionRepositoryImpl() {
        this.connection = ConnectionSingleton.getConnection();
    }

    private final String findById = "select * from question where id = ?";
    private final String findByTopic = "select * from question where topic = ?";
    private final String saveByQuestion = "insert into question(text, topic) values (?, ?)";
    private final String updateByQuestion = "update question set text = ?, topic = ? where id = ?";
    private final String deleteById = "delete from question where id = ?";
    private final String findAllQuestions = "select * from question";
    @Override
    public Question get(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(findById);
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();
            if (!resultSet.next()) return new Question();
            return Question.builder()
                    .id(resultSet.getInt("id"))
                    .topic(resultSet.getString("topic"))
                    .text(resultSet.getString("text"))
                    .build();
        } catch (SQLException e) {
            throw new SQLUpdateException(e.getMessage(), connection);
        }
    }

    @Override
    public Integer save(Question question) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(saveByQuestion);
            preparedStatement.setString(1, question.getText());
            preparedStatement.setString(2, question.getTopic());
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new SQLUpdateException(e.getMessage(), connection);
        }
    }

    @Override
    public void update(Question question) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(updateByQuestion);
            preparedStatement.setString(1, question.getText());
            preparedStatement.setString(2, question.getTopic());
            preparedStatement.setInt(3, question.getId());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new SQLUpdateException(e.getMessage(), connection);
        }
    }

    @Override
    public Integer delete(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(deleteById);
            preparedStatement.setInt(1, id);
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new SQLUpdateException(e.getMessage(), connection);
        }
    }

    @Override
    public List<Question> getByTopic(String topic) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(findByTopic);
            preparedStatement.setString(1, topic);
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();
            List<Question> result = new ArrayList<>();
            while (resultSet.next()) {
                result.add(Question.builder()
                        .id(resultSet.getInt("id"))
                        .topic(resultSet.getString("topic"))
                        .text(resultSet.getString("text"))
                        .build());
            }
            return result;
        } catch (SQLException e) {
            throw new SQLUpdateException(e.getMessage(), connection);
        }
    }

    @Override
    public List<Question> getAll() {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(findAllQuestions);
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();
            List<Question> result = new ArrayList<>();
            while (resultSet.next()) {
                result.add(Question.builder()
                        .id(resultSet.getInt("id"))
                        .topic(resultSet.getString("topic"))
                        .text(resultSet.getString("text"))
                        .build());
            }
            return result;
        } catch (SQLException e) {
            throw new SQLUpdateException(e.getMessage(), connection);
        }
    }
}
