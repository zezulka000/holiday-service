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
@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Holiday not found")
public class HolidayNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2264877914827743341L;

	public HolidayNotFoundException(String message) {
		super(message);
	}

}
