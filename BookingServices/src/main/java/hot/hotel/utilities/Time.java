package hot.hotel.utilities;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import org.springframework.stereotype.Component;

@Component
public class Time {
	public int differenceInDays(String date) {
		//then shall have the format of yyyy-MM-dd
		LocalDate now = LocalDate.now();
		String[] timeArray = date.split("-", 3);
		int year  = Integer.valueOf(timeArray[0]);
		int month = Integer.valueOf(timeArray[1]);
		int day = Integer.valueOf(timeArray[2]);
		
		LocalDate then = LocalDate.of(year, month, day);
		//if it is negative, it is past date
		return (int) ChronoUnit.DAYS.between(now, then);
	}
	 
	public String dateTime() {
		 LocalDateTime now = LocalDateTime.now();
		 
		 DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		 return dtf.format(now);
	}

	 public String daysAfter(int days) {
		 LocalDateTime now = LocalDateTime.now();
		 LocalDateTime then = now.plusDays(days);
		 
		 DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		 return dtf.format(then);
	 }
}
