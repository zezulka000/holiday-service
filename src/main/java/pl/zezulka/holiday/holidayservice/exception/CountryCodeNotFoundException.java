/**
 * 
 */
package pl.zezulka.holiday.holidayservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author ania
 *
 */
@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class CountryCodeNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2264877914827743341L;

	public CountryCodeNotFoundException(String message) {
		super(message);
	}

}
