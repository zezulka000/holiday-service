/**
 * 
 */
package pl.zezulka.holiday.holidayservice.service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import pl.zezulka.holiday.holidayservice.external.IHolidayClient;
import pl.zezulka.holiday.holidayservice.model.CommonHoliday;
import pl.zezulka.holiday.holidayservice.model.Holiday;
import pl.zezulka.holiday.holidayservice.service.impl.HolidayService;

/**
 * @author ania
 *
 */
@ExtendWith(MockitoExtension.class)
public class HolidayServiceTest {

	@Mock
	private IHolidayClient holidayClient;

	private IHolidayService holidayService;

	private Holiday christmasPL = new Holiday("PL", "pl christmas", LocalDate.of(2020, 12, 25));
	private Holiday newYearPL = new Holiday("PL", "pl new year", LocalDate.of(2021, 1, 1));
	private Holiday easterPL = new Holiday("PL", "pl easter", LocalDate.of(2021, 4, 4));

	private Holiday allSaintsDE = new Holiday("DE", "de all saints", LocalDate.of(2020, 11, 1));
	private Holiday christmasDE = new Holiday("DE", "de christmas", LocalDate.of(2020, 12, 25));
	private Holiday newYearDE = new Holiday("DE", "de new year", LocalDate.of(2021, 1, 1));

	public HolidayServiceTest() {
		holidayService = new HolidayService(holidayClient);
	}

	@Test
	public void getCommonHolidayExistTest() {

		List<Holiday> plHolidays = Arrays.asList(christmasPL, newYearPL, easterPL);
		List<Holiday> deHolidays = Arrays.asList(allSaintsDE, christmasDE, newYearDE);

		Optional<CommonHoliday> result = holidayService.getFirstCommonHoliday(plHolidays, deHolidays);

		Assertions.assertTrue(result.isPresent(), "Common holiday found");
		Assertions.assertEquals(result.get(),
				new CommonHoliday(christmasPL.getDate(), christmasPL.getName(), christmasDE.getName()),
				"Common holiday should be found");

	}

	@Test
	public void getCommonHolidayNotFoundTest() {

		List<Holiday> plHolidays = Arrays.asList(christmasPL, newYearPL, easterPL);
		List<Holiday> deHolidays = Arrays.asList(allSaintsDE);

		Optional<CommonHoliday> result = holidayService.getFirstCommonHoliday(plHolidays, deHolidays);

		Assertions.assertFalse(result.isPresent(), "Common holiday should be not found");

	}

}
