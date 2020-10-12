/**
 * 
 */
package pl.zezulka.holiday.holidayservice.util;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import pl.zezulka.holiday.holidayservice.model.Holiday;

/**
 * @author ania
 *
 */
public class HolidayUtils {

	public static List<LocalDate> findCommonDates(List<Holiday> country1Holidays, List<Holiday> country2Holidays) {
		List<LocalDate> result = country1Holidays.stream()
				.flatMap(x -> country2Holidays.stream().filter(y -> x.getDate().isEqual(y.getDate())))
				.map(y -> y.getDate()).collect(Collectors.toList());

		return result;
	}

	public static Optional<LocalDate> findFirstCommonDate(List<Holiday> country1Holidays,
			List<Holiday> country2Holidays) {
		Optional<LocalDate> result = country1Holidays.stream()
				.flatMap(x -> country2Holidays.stream().filter(y -> x.getDate().isEqual(y.getDate())).limit(1))
				.map(y -> y.getDate()).findFirst();
		return result;
	}

}
