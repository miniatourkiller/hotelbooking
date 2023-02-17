package hot.hotel.utilities;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;

@Component
public class SecurityEncryption {
	public String hashPassPin(String passPin) {
		return BCrypt.hashpw(passPin, BCrypt.gensalt());
	}

	public boolean checkPassPin(String hashed, String passPin) {
		return BCrypt.checkpw(passPin, hashed);
	}
}
