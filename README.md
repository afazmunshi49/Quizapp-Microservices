# Quizapp-Microservices
A crud quiz app made using microservices architecture using spring framework.
# Architecture

![quizapp-architecture drawio (1)](https://github.com/user-attachments/assets/22027fbd-95a0-4f99-b89f-4247b0309f44)

# API Gateway IP
* `API_GATEWAY=localhost:8765`

# Endpoints for the Question Service
* `GET {API_GATEWAY}/questions-service/question/all-questions` to get all questions.
* `GET {API_GATEWAY}/question-service/category/{category}` to get questions based on categories.
* `GET {API_GATEWAY}/question-service/generate` to generate random questions for the quiz (give `category` and `numQuestions` in the body)
* `POST {API_GATEWAY}/question-service/add` to add a question to the database.
* `POST {API_GATEWAY}/question-service/score` to get the total socre of the quiz of the quiz has been submitted by the client (The body will expected a list of responses. Each response will have the question id (`id`) and the `response` (string)).

# Endpoints for the Quiz Service
* `GET {API_GATEWAY}/quiz-service/get/{id}` to get quiz created with by id
* `POST {API_GATEWAY}/quiz-service/create` to create a brand new quiz (It expects the `title` of the quiz, `category`, and `numQ` - number of questions to be created random).
* `POST {API_GATEWAY}/quiz-service/submit/{id}` to submit the quiz and get a score for the quiz. This service will call question service in order to evaluate the score (It expects the a list of resposes. Each response will have the question id (`id`) and the `response` (string)).

# Start the application
To run the application, first start the `service-registery` service. This is a `Eureka` server. Next start up `question-service` and `quiz-service`. Finally start `api-gateway` service. This will accept all the requests on port `8765`.
  
