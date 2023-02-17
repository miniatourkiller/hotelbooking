package hot.hotel.utilities;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import hot.hotel.entities.Room;

@Component
public class RoomServices {
public Room getRoom(String r) {
	Room room = new Room();
	
	ObjectMapper om = new ObjectMapper();
	try {
		room = om.readValue(r, Room.class);
	} catch (JsonMappingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (JsonProcessingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return room;
}
}
