/**
 * 
 */
package pl.zezulka.holiday.holidayservice.external;

import java.util.List;

import pl.zezulka.holiday.holidayservice.external.exception.HolidayClientException;
import pl.zezulka.holiday.holidayservice.model.Country;
import pl.zezulka.holiday.holidayservice.model.Holiday;

/**
 * @author ania
 *
 */
public interface IHolidayClient {

	List<Country> getSupportedCountries() throws HolidayClientException;

	List<Holiday> getHolidays(String countryCode, int year) throws HolidayClientException;

}
