package com.learnings.quizapp.model;

import lombok.Data;

@Data
public class QuestionsWrapper {
    private Integer id;
    private String questionDesc;
    private String option1;
    private String option2;
    private String option3;
    private String option4;

    public QuestionsWrapper(Integer id, String questionDesc, String option1, String option2, String option3, String option4) {
        this.id = id;
        this.questionDesc = questionDesc;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
    }
}
