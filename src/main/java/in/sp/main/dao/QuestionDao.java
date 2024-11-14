package in.sp.main.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import in.sp.main.bean.Question;

public interface QuestionDao extends JpaRepository<Question, Integer> {

   List<Question>findByCategory(String category);

   @Query(value = "SELECT * FROM question q WHERE q.category = :category ORDER BY RAND() LIMIT 5", nativeQuery = true)
   List<Question> findRandomQuestionByCategory(String category);
}
