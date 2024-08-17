package com.quizapp.quiz_service.model;

import lombok.Data;

@Data
public class QuizDTO {
    private String category;
    private String title;
    private int numQ;
}
