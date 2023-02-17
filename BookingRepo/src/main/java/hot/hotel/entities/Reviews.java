package hot.hotel.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Reviews {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
private int id;
private int toWhom;//hostid
private String fromWhom;
private String message;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public int getTo() {
	return toWhom;
}
public void setTo(int to) {
	this.toWhom = to;
}
public String getFrom() {
	return fromWhom;
}
public void setFrom(String from) {
	this.fromWhom = from;
}
public String getMessage() {
	return message;
}
public void setMessage(String message) {
	this.message = message;
}

}
