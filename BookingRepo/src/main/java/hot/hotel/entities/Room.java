package hot.hotel.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Room {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
private int id;
private String images;
private String propertyType;
private String amenities;
private int bedroom;
private int bathroom;
private int stars;
private int people;
private String propertyName;
private int price;
private int guests;
private String status;
private String fromDate;
private String toDate;
private String county;
private String area;
private String description;
private int hostId;
private int reviews;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getImages() {
	return images;
}
public void setImages(String images) {
	this.images = images;
}
public String getPropertyType() {
	return propertyType;
}
public void setPropertyType(String propertyType) {
	this.propertyType = propertyType;
}
public String getAmenities() {
	return amenities;
}
public void setAmenities(String amenities) {
	this.amenities = amenities;
}
public int getBedroom() {
	return bedroom;
}
public void setBedroom(int bedroom) {
	this.bedroom = bedroom;
}
public int getBathroom() {
	return bathroom;
}
public void setBathroom(int bathroom) {
	this.bathroom = bathroom;
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
public String getPropertyName() {
	return propertyName;
}
public void setPropertyName(String propertyName) {
	this.propertyName = propertyName;
}
public int getPrice() {
	return price;
}
public void setPrice(int price) {
	this.price = price;
}
public int getGuests() {
	return guests;
}
public void setGuests(int guests) {
	this.guests = guests;
}
public String getStatus() {
	return status;
}
public void setStatus(String status) {
	this.status = status;
}
public String getFromDate() {
	return fromDate;
}
public void setFromDate(String fromDate) {
	this.fromDate = fromDate;
}
public String getToDate() {
	return toDate;
}
public void setToDate(String toDate) {
	this.toDate = toDate;
}
public String getCounty() {
	return county;
}
public void setCounty(String county) {
	this.county = county;
}
public String getArea() {
	return area;
}
public void setArea(String area) {
	this.area = area;
}
public String getDescription() {
	return description;
}
public void setDescription(String description) {
	this.description = description;
}
public int getHostId() {
	return hostId;
}
public void setHostId(int hostId) {
	this.hostId = hostId;
}
public int getReviews() {
	return reviews;
}
public void setReviews(int reviews) {
	this.reviews = reviews;
}

}
