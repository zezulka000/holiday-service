/**
 * 
 */
package pl.zezulka.holiday.holidayservice.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ania
 *
 */
@RestController
public class HelloController {

	@RequestMapping("/hello")
	public String sayHello() {
		return "Greetings from Spring Boot!";
	}

}
