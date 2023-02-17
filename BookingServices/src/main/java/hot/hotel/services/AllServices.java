package hot.hotel.services;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import hot.hotel.dao.ClientDetailsDao;
import hot.hotel.dao.HostDao;
import hot.hotel.dao.ProfilePicDao;
import hot.hotel.dao.ReviewsDao;
import hot.hotel.dao.RoomDao;
import hot.hotel.entities.ClientDetails;
import hot.hotel.entities.Host;
import hot.hotel.entities.ProfilePic;
import hot.hotel.entities.Reviews;
import hot.hotel.entities.Room;
import hot.hotel.essentials.ChangePassword;
import hot.hotel.essentials.EmailContent;
import hot.hotel.essentials.Login;
import hot.hotel.utilities.EmailServices;
import hot.hotel.utilities.Generator;
import hot.hotel.utilities.SecurityEncryption;
import hot.hotel.utilities.SessionUtil;
import hot.hotel.utilities.Time;

@Service
public class AllServices {
	
	@Autowired
	HostDao hDao;
	@Autowired 
	ProfilePicDao pDao;
	@Autowired
	ClientDetailsDao cDao;
	@Autowired
	ReviewsDao rDao;
	@Autowired
	RoomDao roomDao;
	@Autowired
	EmailServices es;
	@Autowired 
	SessionUtil su;
	@Autowired 
	Generator gen;
	@Autowired
	Time time;
	@Autowired
	SecurityEncryption se;
	Host host = new Host();
	Reviews reviews = new Reviews();
	ProfilePic pPic = new ProfilePic();
	Room room = new Room();
	ClientDetails cd = new ClientDetails();
public String register(Host h, String url , String apikey) {
	host = hDao.getByEmail(h.getEmail());
	if(host != null) {
		return "exists";
	}else {
	
		if(es.realEmail(url, apikey, h.getEmail())) {
			h.setVerified(false);
			h.setVkey(gen.generatevkey());
			h.setRetrievalCode(se.hashPassPin("dfdfd"));
			h.setPassword(se.hashPassPin(h.getPassword()));
			hDao.save(h);
			es.verification(h);
			return "done";
		}else {
			return "false email";
		}
	}
}

public String verify(String vkey, String email) {
	host = hDao.vkey(email, vkey);
	if(host == null) {
		return "error";
	}else if(host.isVerified()) {
		
		return "<p>You are already verified and can <a href='http://5070/login.html'>login<a/></p>";
	}else {
		host.setVerified(true);
		hDao.save(host);
		return "<p>You have been successfully verified and can <a href='http://5070/login.html'>login<a/></p>";
	}
}

public String login(Login login, HttpServletRequest req) {
	host = hDao.getByEmail(login.getEmail());
	if(host == null) {
		return null;
	}else {
		if(host.isVerified()) {
			if(se.checkPassPin(host.getPassword(), login.getPassword())) {
				su.createSession(req, host.getId(), host.getEmail());
				return "success";
			}else {
				return "invalid details";
			}
		}else {
			return "not verified";
		}
	}
}

@Autowired
EmailContent ec;
public String retreivalCode(ChangePassword cp) {
	host = hDao.getByEmail(cp.getEmail());
if(host != null) {
	host.setRetrievalCode(gen.generatepasswordretriever());
	hDao.save(host);
	ec.setEmailFrom("jgathiru97@gmail.com");
	ec.setEmailTo(cp.getEmail());
	ec.setSubject("Change password Code");
	ec.setMessage("Your code to reset password is:"+host.getRetrievalCode());
	return "done";
}else {
	return "not registered";
}
}

public String changePassword(ChangePassword cp) {
	host = hDao.retrieval(cp.getEmail(), cp.getCode());
	if(host == null) {
		return "check your details";
	}else {
		host.setPassword(se.hashPassPin(cp.getNewPassword()));
		host.setRetrievalCode(se.hashPassPin("sdfsd"));
		hDao.save(host);
		return "done";
	}
}

public String profilePic(MultipartFile file, HttpSession session) {
	if(su.checkSession(session)) {
		return "expired";
	}else {
		host = hDao.getByEmail(su.getSessionArray(session)[1]);
		try {
			file.transferTo(new File("C:\\Users\\User\\Documents\\workspace-spring-tool-suite-4-4.14.0.RELEASE\\HotelBooking\\BookingMain\\src\\main\\resources\\public\\hostimages\\"+file.getOriginalFilename()));
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		pPic.setImageName(file.getOriginalFilename());
		pPic.setOwnerId(host.getId());
		pDao.save(pPic);
		return "done";
	}
}

public Host gethost(int id) {
	host = hDao.getById(id);
	return host;
}

public String postRoom(ArrayList<MultipartFile> files, Room r, HttpSession session) {
	if(!su.checkSession(session)) {
		return "expired";
	}else {
		host = hDao.getByEmail(su.getSessionArray(session)[1]);
		String imageNames = "";
		for(MultipartFile file : files) {
			imageNames += file.getOriginalFilename()+":";
			try {
				file.transferTo(new File("C:\\Users\\User\\Documents\\workspace-spring-tool-suite-4-4.14.0.RELEASE\\HotelBooking\\BookingMain\\src\\main\\resources\\public\\hotelimages\\"+file.getOriginalFilename()));
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		r.setImages(imageNames);
		r.setHostId(host.getId());
		r.setStatus("available");
		roomDao.save(r);
		return "done";
	}
}

public String bookRoom(EmailContent e) {
	es.sendSimpleMail(e);
	return "done";
}

public String upDateStatus() {
	ArrayList<Room> rooms = (ArrayList<Room>) roomDao.findAll();
	for(Room room : rooms) {
		if(room.getStatus() == "booked") {
			if(time.differenceInDays(room.getToDate())<0) {
				room.setStatus("available");
				roomDao.save(room);
				
			}
		}
	}
	return "done";
}

public String changeStatus(ClientDetails c,HttpSession session, Integer roomid) {
	if(su.checkSession(session)) {
		return "expired";
	}else {
		host = hDao.getByEmail(su.getSessionArray(session)[1]);
		room = roomDao.getById(roomid);
		if(room.getStatus() == "available") {
			room.setStatus("booked");
			room.setFromDate(c.getDateFrom());
			room.setToDate(c.getCheckOutDate());
			roomDao.save(room);
			cDao.save(c);
			ec.setEmailFrom(host.getEmail());
			ec.setEmailTo(c.getContact());
			ec.setMessage("Dear "+c.getName()+",Your booking has been verified. You can view the room in this site. http://5070/house.html?id="+String.valueOf(roomid));
			ec.setSubject("Booking verification");
			es.sendSimpleMail(ec);
			return "done";
		}else {
			room.setStatus("available");
			room.setToDate(null);
			room.setFromDate(null);
			roomDao.save(room);
			return "done";
		}
	}
}

public ArrayList<Room> getHostRooms(HttpSession session){//this is for the host
	if(!su.checkSession(session)) {
		return null;
	}else {
		ArrayList<Room> rooms = roomDao.getHostRooms(Integer.valueOf(su.getSessionArray(session)[0]) );
		if(rooms != null) {
			ArrayList<Room> rs = new ArrayList<>();

		int counter  = rooms.size();
		ArrayList<Integer> tracker = new ArrayList<>(counter);
		int rand;
		do {
			rand = random.nextInt(counter);
			if(!tracker.contains(rand)) {
				tracker.add(rand);
			}
		}while(tracker.size()<counter);
		for(int i =0; i<counter; i++) {
			rs.add(rooms.get(tracker.get(i)));
		}
		return rs;
		}

		return null;
	}
}
public ArrayList<Room> getHostRooms(int id){//this is for the viewers

		ArrayList<Room> rooms = roomDao.getHostRooms(id);
		if(rooms != null) {
			ArrayList<Room> rs = new ArrayList<>();

		int counter  = rooms.size();
		ArrayList<Integer> tracker = new ArrayList<>(counter);
		int rand;
		do {
			rand = random.nextInt(counter);
			if(!tracker.contains(rand)) {
				tracker.add(rand);
			}
		}while(tracker.size()<counter);
		for(int i =0; i<counter; i++) {
			rs.add(rooms.get(tracker.get(i)));
		}
		return rs;
		}else {
			return null;
		}

		
}

public String removeRoom(HttpSession session, Integer id) {
	if(su.checkSession(session)) {
		return "expired";
	}else {
		room = roomDao.getById(id);
		if(room.getStatus() == "booked") {
			return "booked";
		}
		roomDao.delete(room);
		return "done";
	}
}
Random random = new Random();
public ArrayList<Room> rooms(){
	ArrayList<Room> rooms = (ArrayList<Room>) roomDao.findAll();
	if(rooms != null) {
		ArrayList<Room> rs = new ArrayList<>();

	int counter  = rooms.size();
	ArrayList<Integer> tracker = new ArrayList<>(counter);
	int rand;
	do {
		rand = random.nextInt(counter);
		if(!tracker.contains(rand)) {
			tracker.add(rand);
		}
	}while(tracker.size()<counter);
	for(int i =0; i<counter; i++) {
		rs.add(rooms.get(tracker.get(i)));
	}
	return rs;
	}

	return null;
}

public ArrayList<Room> countyFiltered(String county) {
	ArrayList<Room> rooms = roomDao.getByCounty(county);
	if(rooms != null) {
		ArrayList<Room> rs = new ArrayList<>();

	int counter  = rooms.size();
	ArrayList<Integer> tracker = new ArrayList<>(counter);
	int rand;
	do {
		rand = random.nextInt(counter);
		if(!tracker.contains(rand)) {
			tracker.add(rand);
		}
	}while(tracker.size()<counter);
	for(int i =0; i<counter; i++) {
		rs.add(rooms.get(tracker.get(i)));
	}
	return rs;
	}

	return null;
}

public ArrayList<Room> countyPropertyTypefiltered(String county, String propertyType){
	ArrayList<Room> rooms = roomDao.getByCountyPropertyType(county, propertyType);
	if(rooms != null) {
		ArrayList<Room> rs = new ArrayList<>();

	int counter  = rooms.size();
	ArrayList<Integer> tracker = new ArrayList<>(counter);
	int rand;
	do {
		rand = random.nextInt(counter);
		if(!tracker.contains(rand)) {
			tracker.add(rand);
		}
	}while(tracker.size()<counter);
	for(int i =0; i<counter; i++) {
		rs.add(rooms.get(tracker.get(i)));
	}
	return rs;
	}

	return null;
}

public String emailHost(Integer id, EmailContent e) {
	host = hDao.getById(id);
	e.setEmailTo(host.getEmail());
	es.sendSimpleMail(e);
	return "done";
}

public Room getRoombyid(Integer id) {
	room = roomDao.getById(id);
	if(room == null) {
		return null;
	}
	return room;
}

public String writeReviews(int id, Reviews r) {//the id of the house being reviewed
	r.setTo(id);
	room = roomDao.getById(id);
	room.setReviews(room.getReviews()+1);
	rDao.save(r);
	return "done";
}

public ArrayList<Reviews> getRevies(int id) {//the id of the house being reviewed
	ArrayList<Reviews> reviews = rDao.getByTo(id);
	if(reviews != null) {
		ArrayList<Reviews> rs = new ArrayList<>();

	int counter  = reviews.size();
	ArrayList<Integer> tracker = new ArrayList<>(counter);
	int rand;
	do {
		rand = random.nextInt(counter);
		if(!tracker.contains(rand)) {
			tracker.add(rand);
		}
	}while(tracker.size()<counter);
	for(int i =0; i<counter; i++) {
		rs.add(reviews.get(tracker.get(i)));
	}
	return rs;
	}

	return null;
}
}
