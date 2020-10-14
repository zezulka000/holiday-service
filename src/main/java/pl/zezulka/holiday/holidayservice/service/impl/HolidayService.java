package pl.zezulka.holiday.holidayservice.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import pl.zezulka.holiday.holidayservice.external.IHolidayClient;
import pl.zezulka.holiday.holidayservice.external.exception.HolidayClientException;
import pl.zezulka.holiday.holidayservice.model.CommonHoliday;
import pl.zezulka.holiday.holidayservice.model.Country;
import pl.zezulka.holiday.holidayservice.model.Holiday;
import pl.zezulka.holiday.holidayservice.service.IHolidayService;

/**
 * 
 * @author ania
 *
 */
@Service
public class HolidayService implements IHolidayService {

	private IHolidayClient holidayClient;

	public HolidayService(IHolidayClient holidayClient) {
		super();
		this.holidayClient = holidayClient;
	}

	@Override
	@Cacheable("countries")
	public List<Country> getSupportedCountries() throws HolidayClientException {
		return holidayClient.getSupportedCountries();
	}

	@Override
	public List<Holiday> getUpcommingHolidays(String countryCode, LocalDate date) throws HolidayClientException {

		List<Holiday> currentYearHolidays = holidayClient.getHolidays(countryCode, date.getYear());

		List<Holiday> upcommingHolidays = currentYearHolidays.stream().filter(h -> !h.getDate().isBefore(date))
				.collect(Collectors.toList());

		return upcommingHolidays;

	}

	@Override
	public Optional<CommonHoliday> getFirstCommonHoliday(List<Holiday> country1Holidays,
			List<Holiday> country2Holidays) {

		Optional<CommonHoliday> result = country1Holidays.stream()
				.flatMap(i -> country2Holidays.stream().filter(j -> i.getDate().isEqual(j.getDate())).limit(1)
						.map(j -> new CommonHoliday(j.getDate(), i.getName(), j.getName())))
				.findFirst();

		return result;

	}

}
