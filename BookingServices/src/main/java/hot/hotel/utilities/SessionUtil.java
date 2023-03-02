package hot.hotel.utilities;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;

@Component
public class SessionUtil {
	public String createSession(HttpServletRequest req, int hostId, String hostEmail) {
		
		req.getSession().setAttribute("details",""+hostId+":"+hostEmail);
		return "done";
	}

	public String[] getSessionArray(HttpSession session) {
		
		return ((String) session.getAttribute("details")).split(":", 2);
	}
	public boolean checkSession(HttpSession session) {
		if((String) session.getAttribute("details") != null) {
			
			return true;
		}
		return false;
	}

	public String destroySession(HttpSession session) {
		session.invalidate();
		return "done";
	}
}
