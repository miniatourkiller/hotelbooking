package hot.hotel.main;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import hot.hotel.essentials.ChangePassword;
import hot.hotel.essentials.EmailContent;
import hot.hotel.essentials.Login;

@Configuration
public class config {
@Bean
EmailContent setEmailContent() {
	return new EmailContent();
}
@Bean
Login setLogin() {
	return new Login();
}
@Bean
ChangePassword setChangePassword() {
	return new ChangePassword();
}
}
