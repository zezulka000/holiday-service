package pl.zezulka.holiday.holidayservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class HolidayServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(HolidayServiceApplication.class, args);
	}

}
