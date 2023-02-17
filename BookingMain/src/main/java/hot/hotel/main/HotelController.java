package hot.hotel.main;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import hot.hotel.entities.ClientDetails;
import hot.hotel.entities.Host;
import hot.hotel.entities.Reviews;
import hot.hotel.entities.Room;
import hot.hotel.essentials.ChangePassword;
import hot.hotel.essentials.EmailContent;
import hot.hotel.essentials.Login;
import hot.hotel.services.AllServices;
import hot.hotel.utilities.RoomServices;

@RestController
public class HotelController {
@RequestMapping(value="/home/{name}/{age}")
public String test(HttpServletResponse res, @PathVariable("name") String name, @PathVariable("age") Integer age) {
	res.setContentType("HTML");
	return "<h3>Hello "+name+age+" </h3>";
}
@Autowired
AllServices all;

@Value("${api.key}")
String apiKey;
@Value("${api.url}")
String url;
@RequestMapping(value = "register", method = RequestMethod.POST)
public String register(@RequestBody Host host) {
return all.register(host, url, apiKey);
}

@RequestMapping(value = "verify/{vkey}/{email}", method = RequestMethod.POST)
public String verify(@PathVariable("vkey") String vkey, @PathVariable("email") String email, HttpServletResponse res) {
	res.setContentType("HTML");
	return all.verify(vkey, email);
}

@RequestMapping(value = "login", method = RequestMethod.POST)
public String login(@RequestBody Login login, HttpServletRequest req) {
	return all.login(login, req);
}

@RequestMapping(value = "retrievecode", method = RequestMethod.POST)
public String retrieve(@RequestBody ChangePassword cp) {
	return all.retreivalCode(cp);
}
@RequestMapping(value = "changepassword", method = RequestMethod.POST)
public String changep(@RequestBody ChangePassword cp) {
	return all.changePassword(cp);
}

@RequestMapping(value = "profilepicupload", method = RequestMethod.POST)
public String profilepic(@RequestParam("file") MultipartFile file, HttpServletRequest req) {
	return all.profilePic(file, req.getSession());
}

@RequestMapping(value = "showrooms", method = RequestMethod.GET)
public ArrayList<Room> showrooms(){
	return all.rooms();
}

@RequestMapping(value = "updatestatus", method = RequestMethod.GET)
public String updatestatus() {
	return all.upDateStatus();
}
@RequestMapping(value = "gethost/{hostid}", method = RequestMethod.GET)
public Host gethost(@PathVariable("hostid") int id ) {
	return all.gethost(id);
}

@RequestMapping(value = "changestatus/{roomid}", method = RequestMethod.POST)
public String changestatus(ClientDetails cd, HttpServletRequest req,@PathVariable("roomid") Integer roomid) {
	return all.changeStatus(cd, req.getSession(), roomid);
}

@Autowired
RoomServices rs;
@RequestMapping(value = "posting", method = RequestMethod.POST)
public String posting(@RequestPart("files") ArrayList<MultipartFile> files, @RequestPart("data") String r, HttpServletRequest req) {
	Room room = rs.getRoom(r);
	return all.postRoom(files, room, req.getSession());
}

@RequestMapping(value = "deleteroom/{roomid}", method = RequestMethod.POST)
public String deleteroom(HttpServletRequest req, @PathVariable("roomid") Integer id) {
	return all.removeRoom(req.getSession(), id);
}

@RequestMapping(value = "countyfiltered/{county}", method = RequestMethod.POST)
public ArrayList<Room> countyfiltered(@PathVariable("county") String county){
	return all.countyFiltered(county);
}
@RequestMapping(value = "countypropertytypefiltered/{county}/{propertytype}", method = RequestMethod.POST)
public ArrayList<Room> countyproperty(@PathVariable("county") String county, @PathVariable("propertytype") String propertyType){
	return all.countyPropertyTypefiltered(county, propertyType);
}
@RequestMapping(value = "review/{id}", method = RequestMethod.POST)
public String review(@RequestBody Reviews r, @PathVariable("id") int id) {
	return all.writeReviews(id, r);
}

@RequestMapping(value = "getReviews/{id}", method = RequestMethod.POST)
public ArrayList<Reviews> getReviews(@PathVariable("id") int id){
	return all.getRevies(id);
}
@RequestMapping("showhostrooms/{id}")
public ArrayList<Room> hostRooms(@PathVariable("id") int id){
	System.out.println(id);
	return all.getHostRooms(Integer.valueOf(id));
}

@RequestMapping(value = "hostpostedrooms", method = RequestMethod.GET)
public ArrayList<Room> hostPostedrooms(HttpServletRequest req){
	return all.getHostRooms(req.getSession());
}

@RequestMapping(value = "emailhost/{hostid}", method = RequestMethod.POST)
public String emailhost(@PathVariable("hostid") Integer id, @RequestBody EmailContent e) {
	return all.emailHost(id, e);
}
}
