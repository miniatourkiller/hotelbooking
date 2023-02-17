package hot.hotel.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Host {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
private int id;
private String hostName;
private int stars;
private int  people;
private String email;
private String phoneNumber;
private String password;
private boolean verified;
private String vkey;
private String retrievalCode;

public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getHostName() {
	return hostName;
}
public void setHostName(String hostName) {
	this.hostName = hostName;
}
public int getStars() {
	return stars;
}
public void setStars(int stars) {
	this.stars = stars;
}
public int getPeople() {
	return people;
}
public void setPeople(int people) {
	this.people = people;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getPhoneNumber() {
	return phoneNumber;
}
public void setPhoneNumber(String phoneNumber) {
	this.phoneNumber = phoneNumber;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public boolean isVerified() {
	return verified;
}
public void setVerified(boolean verified) {
	this.verified = verified;
}
public String getVkey() {
	return vkey;
}
public void setVkey(String vkey) {
	this.vkey = vkey;
}
public String getRetrievalCode() {
	return retrievalCode;
}
public void setRetrievalCode(String retrievalCode) {
	this.retrievalCode = retrievalCode;
}


}
