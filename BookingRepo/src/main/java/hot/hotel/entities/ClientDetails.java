package hot.hotel.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ClientDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
private int id;
private String name;
private String contact;
private int roomId;
private String dateFrom;
private String checkOutDate;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getContact() {
	return contact;
}
public void setContact(String contact) {
	this.contact = contact;
}
public String getDateFrom() {
	return dateFrom;
}
public void setDateFrom(String dateFrom) {
	this.dateFrom = dateFrom;
}
public String getCheckOutDate() {
	return checkOutDate;
}
public void setCheckOutDate(String checkOutDate) {
	this.checkOutDate = checkOutDate;
}
public int getRoomId() {
	return roomId;
}
public void setRoomId(int roomId) {
	this.roomId = roomId;
}


}
