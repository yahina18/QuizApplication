package in.sp.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import in.sp.main.bean.QuestionWrapper;
import in.sp.main.bean.Response;
import in.sp.main.service.QuizService;

@RestController
@RequestMapping("/quiz")
public class QuizController {

    @Autowired
	QuizService quizService;
	
	@GetMapping("/create")
	public String createQuiz(@RequestParam String category,@RequestParam int nums, @RequestParam String title)
	{
		quizService.createQuiz(category, nums, title);
	    return "Created Quiz Successfully";
	}
	
	@GetMapping("/get/{id}")
	public ResponseEntity<List<QuestionWrapper>> getQuiz(@PathVariable int id)
	{
		return new ResponseEntity(quizService.getQuiz(id),HttpStatus.OK);
				
	}
	
	@PostMapping("/submit/{id}")
	public ResponseEntity<Integer> submitQuiz(@PathVariable int id, @RequestBody List<Response> response)
	{
		return new ResponseEntity(quizService.submitQuiz(id,response), HttpStatus.OK);
			
	}
	
	
	
}
