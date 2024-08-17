package com.quizapp.quiz_service.services;

import com.quizapp.quiz_service.dao.QuizDao;
import com.quizapp.quiz_service.feign.QuizInterface;
import com.quizapp.quiz_service.model.Questions;
import com.quizapp.quiz_service.model.QuestionsWrapper;
import com.quizapp.quiz_service.model.Quiz;
import com.quizapp.quiz_service.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuizService {
    private final QuizDao _quizDao;

    @Autowired
    QuizInterface _quizInterface;

    @Autowired
    public QuizService(QuizDao quizDao) {
        _quizDao = quizDao;
    }

    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {
       try {
          List<Integer> questions = _quizInterface.generateQuestionsForQuiz(category, numQ).getBody();
          Quiz quiz = new Quiz();
          quiz.setTitle(title);
          quiz.setQuestionIds(questions);
          _quizDao.save(quiz);
           return new ResponseEntity<>("Success", HttpStatus.CREATED);
       } catch (Exception e) {
           e.printStackTrace();
       }

       return new ResponseEntity<>("Error", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public ResponseEntity<List<QuestionsWrapper>> getQuizQuestions(Integer id) {
        Quiz quiz = _quizDao.findById(id).get();
        List<Integer> questionIds = quiz.getQuestionIds();
        return _quizInterface.getQuestionsFromId(questionIds);
    }

    public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
        int result = 0;
        try {
            result = _quizInterface.getScore(responses).getBody();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
