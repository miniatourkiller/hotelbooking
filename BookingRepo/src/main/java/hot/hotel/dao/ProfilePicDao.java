package hot.hotel.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import hot.hotel.entities.ProfilePic;

public interface ProfilePicDao extends JpaRepository<ProfilePic, Integer> {

}
