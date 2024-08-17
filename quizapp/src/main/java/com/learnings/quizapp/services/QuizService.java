package com.learnings.quizapp.services;

import com.learnings.quizapp.dao.QuestionDao;
import com.learnings.quizapp.dao.QuizDao;
import com.learnings.quizapp.model.Questions;
import com.learnings.quizapp.model.QuestionsWrapper;
import com.learnings.quizapp.model.Quiz;
import com.learnings.quizapp.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuizService {
    private final QuizDao _quizDao;
    private final QuestionDao _questionDao;

    @Autowired
    public QuizService(QuizDao quizDao, QuestionDao questionDao) {
        _quizDao = quizDao;
        _questionDao = questionDao;
    }

    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {
       try {
           List<Questions> questions = _questionDao.findRandomQuestionsByCategory(category, numQ);

           Quiz quiz = new Quiz();
           quiz.setTitle(title);
           quiz.setQuestions(questions);
           _quizDao.save(quiz);

           return new ResponseEntity<>("Success", HttpStatus.CREATED);
       } catch (Exception e) {
           e.printStackTrace();
       }

       return new ResponseEntity<>("Error", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public ResponseEntity<List<QuestionsWrapper>> getQuizQuestions(Integer id) {
        Quiz quiz = _quizDao.findById(id).get();
        List<Questions> QuestionsFromDB = quiz.getQuestions();
        List<QuestionsWrapper> questionsWrappers = new ArrayList<>();

        for (Questions question : QuestionsFromDB) {
            questionsWrappers.add(new QuestionsWrapper(question.getId(),
                    question.getQuestionDesc(),
                    question.getOption1(),
                    question.getOption2(),
                    question.getOption3(),
                    question.getOption4())
            );
        }

        return new ResponseEntity<>(questionsWrappers, HttpStatus.OK);
    }

    public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
        int result = 0;
        Quiz quiz = _quizDao.findById(id).get();
        List<Questions> questionsFromDB = quiz.getQuestions();
        int i = 0;
        for (Response response : responses) {
            if (response.getResponse().equals(questionsFromDB.get(i).getAnswer())) {
                result += 1;
            }
            i++;
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
