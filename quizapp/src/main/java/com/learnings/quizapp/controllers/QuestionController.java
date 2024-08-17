package com.learnings.quizapp.controllers;

import com.learnings.quizapp.model.Questions;
import com.learnings.quizapp.services.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("question")
public class QuestionController {
    private final QuestionService _questionService;

    @Autowired
    public QuestionController (QuestionService questionService) {
        this._questionService = questionService;
    }

    @GetMapping("all-questions")
    public ResponseEntity<List<Questions>> getAllQuestions() {
        return _questionService.getAllQuestions();
    }

    @GetMapping("category/{category}")
    public ResponseEntity<List<Questions>> getQuestionsByCategory(@PathVariable String category) {
        return _questionService.getQuestionsByCategory(category);
    }

    @PostMapping("add")
    public ResponseEntity<String> addQuestion(@RequestBody Questions question) {
        return _questionService.addQuestion(question);
    }
}
