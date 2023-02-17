package hot.hotel.dao;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import hot.hotel.entities.Room;

public interface RoomDao extends JpaRepository<Room, Integer>{
	@Query("FROM Room r WHERE r.hostId = ?1")
	ArrayList<Room> getHostRooms(int hostId);
	@Query("FROM Room r WHERE r.county = ?1 AND r.propertyType = ?2")
	ArrayList<Room> getByCountyPropertyType(String county, String propertyType);
	@Query("FROM Room r WHERE r.county = ?1")
	ArrayList<Room> getByCounty(String county);
}
