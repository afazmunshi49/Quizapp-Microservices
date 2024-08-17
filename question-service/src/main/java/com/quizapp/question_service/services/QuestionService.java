package com.quizapp.question_service.services;

import com.quizapp.question_service.dao.QuestionDao;
import com.quizapp.question_service.model.Questions;
import com.quizapp.question_service.model.QuestionsWrapper;
import com.quizapp.question_service.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {
    private final QuestionDao _questionDao;

    @Autowired
    public QuestionService(QuestionDao questionDao) {
        _questionDao = questionDao;
    }

    public ResponseEntity<List<Questions>> getAllQuestions() {
        try {
            return new ResponseEntity<>(_questionDao.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<Questions>> getQuestionsByCategory(String category) {
        try {
            return new ResponseEntity<>(_questionDao.findByCategory(category), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> addQuestion(Questions question) {
        try {
            _questionDao.save(question);
            return new ResponseEntity<>("success", HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("fail", HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<Integer>> generateQuestionsForQuiz(String category, Integer numQuestions) {
        List<Integer> questions = _questionDao.findRandomQuestionsByCategory(category, numQuestions);
        return new ResponseEntity<>(questions, HttpStatus.OK);
    }

    public ResponseEntity<List<QuestionsWrapper>> getQuestionsFromId(List<Integer> questionIds) {
        List<QuestionsWrapper> result = new ArrayList<>();
        for (Integer questionId : questionIds) {
            Questions q = _questionDao.findById(questionId).get();
            result.add(new QuestionsWrapper(
                    q.getId(),
                    q.getQuestionDesc(),
                    q.getOption1(),
                    q.getOption2(),
                    q.getOption3(),
                    q.getOption4())
            );
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    public ResponseEntity<Integer> getScore(List<Response> responses) {
        int result = 0;
        for (int i = 0; i < responses.size(); i++) {
            Response response = responses.get(i);
            Questions question = _questionDao.findById(response.getId()).get();
            if (question.getAnswer().equals(response.getResponse())) {
                result += 1;
            }
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
