package hot.hotel.utilities;

import java.util.Random;

import org.springframework.stereotype.Component;

@Component
public class Generator {
	String arr = "";
	Random random = new Random();
	public String generatepasswordretriever() {
		String code = "";
		arr="1234567890ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		for(int i=0; i<6; i++) {
			int no = random.nextInt(arr.length());
			code+=arr.charAt(no);
		}

		return code;
	}
	public String generatevkey() {
		String code ="";
		arr="1234567890ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		for(int i=0; i<16; i++) {
			int no = random.nextInt(arr.length());
			code+=arr.charAt(no);
		}
		return code;
	}
}
