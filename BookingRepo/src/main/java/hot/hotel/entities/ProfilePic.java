package hot.hotel.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ProfilePic {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
private int id;
private int ownerId;
private String imageName;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public int getOwnerId() {
	return ownerId;
}
public void setOwnerId(int ownerId) {
	this.ownerId = ownerId;
}
public String getImageName() {
	return imageName;
}
public void setImageName(String imageName) {
	this.imageName = imageName;
}


}
