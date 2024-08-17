package com.quizapp.question_service.controllers;

import com.quizapp.question_service.model.Questions;
import com.quizapp.question_service.model.QuestionsWrapper;
import com.quizapp.question_service.model.Response;
import com.quizapp.question_service.services.QuestionService;
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

    @GetMapping("generate")
    public ResponseEntity<List<Integer>> generateQuestionsForQuiz(@RequestParam String category, @RequestParam Integer numQuestions) {
        return _questionService.generateQuestionsForQuiz(category, numQuestions);
    }

    @PostMapping("get-questions")
    public ResponseEntity<List<QuestionsWrapper>> getQuestionsFromId(@RequestBody List<Integer> questionIds) {
        return _questionService.getQuestionsFromId(questionIds);
    }

    @PostMapping("score")
    public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses) {
        return _questionService.getScore(responses);
    }
}
