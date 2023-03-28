package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Question {
    private Integer id;
    private String text;
    private String topic;
    public boolean isEmpty() {
        return Objects.isNull(id) && Objects.isNull(text) && Objects.isNull(topic);
    }
    public static Question buildFromString(String s) {
        String[] divided = s.split(" ");
        Question question = new Question();
        if (divided.length >= 2) {
            question.setText(Arrays.stream(divided).limit(divided.length - 1).collect(Collectors.joining(" ")));
            question.setTopic(divided[divided.length - 1]);
        } else if (divided.length == 1){
            question.setText(divided[0]);
            question.setTopic("default");
        } else {
            question.setText("default");
            question.setTopic("default");
        }
        return question;
    }
}
