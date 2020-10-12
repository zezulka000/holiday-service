/**
 * 
 */
package pl.zezulka.holiday.holidayservice.model;

/**
 * @author ania
 *
 */
public class Country {

	private final String code;

	public Country(String code) {
		super();
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	@Override
	public String toString() {
		return "Country [code=" + code + "]";
	}

}
