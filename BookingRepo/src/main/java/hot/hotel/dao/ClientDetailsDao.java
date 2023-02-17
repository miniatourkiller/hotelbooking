package hot.hotel.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import hot.hotel.entities.ClientDetails;

public interface ClientDetailsDao extends JpaRepository<ClientDetails, Integer>{

}
