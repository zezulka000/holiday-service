/**
 * 
 */
package pl.zezulka.holiday.holidayservice.external.exception;

/**
 * @author ania
 *
 */
//leave for 500 status for now
public class HolidayClientException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2264877914827743341L;

	public HolidayClientException() {
		super();
	}

	public HolidayClientException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public HolidayClientException(String message, Throwable cause) {
		super(message, cause);
	}

	public HolidayClientException(String message) {
		super(message);
	}

	public HolidayClientException(Throwable cause) {
		super(cause);
	}

}
