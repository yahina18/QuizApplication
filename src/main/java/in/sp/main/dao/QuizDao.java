package in.sp.main.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import in.sp.main.bean.Quiz;

public interface QuizDao extends JpaRepository<Quiz, Integer> {

}
