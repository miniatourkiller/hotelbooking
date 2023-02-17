package hot.hotel.dao;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import hot.hotel.entities.Reviews;

public interface ReviewsDao extends JpaRepository<Reviews, Integer>{
	@Query("FROM Reviews r WHERE r.toWhom = ?1")
	ArrayList<Reviews> getByTo(int to);
}
