package com.quizapp.quiz_service.controllers;

import com.quizapp.quiz_service.model.QuestionsWrapper;
import com.quizapp.quiz_service.model.QuizDTO;
import com.quizapp.quiz_service.model.Response;
import com.quizapp.quiz_service.services.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<String> createQuiz(@RequestBody QuizDTO quizDTO) {
        return _quizService.createQuiz(quizDTO.getCategory(), quizDTO.getNumQ(), quizDTO.getTitle());
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
