package hot.hotel.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import hot.hotel.entities.Host;

public interface HostDao extends JpaRepository<Host, Integer>{
@Query("FROM Host h WHERE h.email = ?1")
Host getByEmail(String email);
@Query("FROM Host h WHERE h.email = ?1 AND h.vkey = ?2")
Host vkey(String email, String vkey);
@Query("FROM Host h WHERE h.email = ?1 AND h.retrievalCode = ?2")
Host retrieval(String email, String retrievalCode);
}
