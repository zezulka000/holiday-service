/**
 * 
 */
package pl.zezulka.holiday.holidayservice.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import pl.zezulka.holiday.holidayservice.external.exception.HolidayClientException;
import pl.zezulka.holiday.holidayservice.model.CommonHoliday;
import pl.zezulka.holiday.holidayservice.model.Country;
import pl.zezulka.holiday.holidayservice.model.Holiday;

/**
 * @author ania
 *
 */
public interface IHolidayService {

	List<Country> getSupportedCountries() throws HolidayClientException;

	List<Holiday> getUpcommingHolidays(String countryCode, LocalDate date) throws HolidayClientException;

	Optional<CommonHoliday> getFirstCommonHoliday(List<Holiday> country1Holidays, List<Holiday> country2Holidays);

}
