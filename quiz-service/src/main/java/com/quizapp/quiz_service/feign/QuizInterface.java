package com.quizapp.quiz_service.feign;

import com.quizapp.quiz_service.model.QuestionsWrapper;
import com.quizapp.quiz_service.model.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("QUESTION-SERVICE")
public interface QuizInterface {
    @GetMapping("question/generate")
    public ResponseEntity<List<Integer>> generateQuestionsForQuiz(@RequestParam String category, @RequestParam Integer numQuestions);

    @PostMapping("question/get-questions")
    public ResponseEntity<List<QuestionsWrapper>> getQuestionsFromId(@RequestBody List<Integer> questionIds);

    @PostMapping("question/score")
    public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses);
}
