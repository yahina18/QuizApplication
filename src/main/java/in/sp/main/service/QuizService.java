package in.sp.main.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

import in.sp.main.bean.Question;
import in.sp.main.bean.QuestionWrapper;
import in.sp.main.bean.Quiz;
import in.sp.main.bean.Response;
import in.sp.main.dao.QuestionDao;
import in.sp.main.dao.QuizDao;

@Service
public class QuizService {

	@Autowired
	QuizDao quizDao;
	
	@Autowired
	QuestionDao questionDao;

    
	public ResponseEntity<String> createQuiz(String category, int nums, String title) {
		
		Quiz quiz = new Quiz();
		List<Question> questions = questionDao.findRandomQuestionByCategory(category);
		quiz.setQuestionTitle(title);
		quiz.setQuestion(questions);
		
		return new ResponseEntity(quizDao.save(quiz),HttpStatus.CREATED);	
	}
	
	
	public ResponseEntity<List<QuestionWrapper>> getQuiz(int id)
	{
		   Quiz quiz = quizDao.findById(id).get();
		   List<Question> questionFromDb = quiz.getQuestion();
		   List<QuestionWrapper> questionForUser = new ArrayList<>();
		   
		   for(Question q : questionFromDb)
		   {
			   QuestionWrapper qw = new QuestionWrapper(q.getId(), q.getQuestionTitle(), q.getOption1(), q.getOption2(), q.getOption3(), q.getOption4());
			   questionForUser.add(qw);
		   }
		   return new ResponseEntity(questionForUser,HttpStatus.OK);
	}


	public ResponseEntity<Integer> submitQuiz(int id, List<Response> response) {
		
		 Quiz quiz =  quizDao.findById(id).get();
		 List<Question> questionList = quiz.getQuestion();
		 int i = 0;
		 int right = 0;
		 for(Response resp:response)
		 {
			if(resp.getResponse().equals(questionList.get(i).getRightAnswer()))
				right++;
			
			i++;			 
		 }
		 
		 System.out.println("Right Answer count : "+i);
		 
		 return new ResponseEntity(right, HttpStatus.OK);
		 	
	}
	
	
	
	
}
