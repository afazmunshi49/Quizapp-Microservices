package com.learnings.quizapp.controllers;

import com.learnings.quizapp.model.QuestionsWrapper;
import com.learnings.quizapp.model.Quiz;
import com.learnings.quizapp.model.Response;
import com.learnings.quizapp.services.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
public class QuizController {
    private final QuizService _quizService;

    @Autowired
    public QuizController(QuizService quizService) {
        this._quizService = quizService;
    }

    @PostMapping("create")
    public ResponseEntity<String> createQuiz(@RequestParam String category, @RequestParam int numQ, @RequestParam String title) {
        return _quizService.createQuiz(category, numQ, title);
    }

    @GetMapping("get/{id}")
    public ResponseEntity<List<QuestionsWrapper>> getQuizQuestions(@PathVariable Integer id) {
        return _quizService.getQuizQuestions(id);
    }

    @PostMapping("submit/{id}")
    public ResponseEntity<Integer> submitQuiz(@PathVariable Integer id, @RequestBody List<Response> responses) {
        return _quizService.calculateResult(id, responses);
    }
}
