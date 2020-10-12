package pl.zezulka.holiday.holidayservice.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import pl.zezulka.holiday.holidayservice.external.IHolidayClient;
import pl.zezulka.holiday.holidayservice.external.exception.HolidayClientException;
import pl.zezulka.holiday.holidayservice.model.CommonHoliday;
import pl.zezulka.holiday.holidayservice.model.Country;
import pl.zezulka.holiday.holidayservice.model.Holiday;
import pl.zezulka.holiday.holidayservice.service.IHolidayService;
import pl.zezulka.holiday.holidayservice.util.HolidayUtils;

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

		LocalDate futureDate = date.plusYears(1);

		List<Holiday> currentYearHolidays = holidayClient.getHolidays(countryCode, date.getYear());
		List<Holiday> nextYearHolidays = holidayClient.getHolidays(countryCode, futureDate.getYear());

		List<Holiday> holidays = Stream
				.concat(currentYearHolidays.stream().filter(h -> !h.getDate().isBefore(date)),
						nextYearHolidays.stream().filter(h -> h.getDate().isBefore(futureDate)))
				.collect(Collectors.toList());

		return holidays;
	}

	@Override
	public Optional<CommonHoliday> getCommonHoliday(List<Holiday> country1Holidays, List<Holiday> country2Holidays) {
		Optional<LocalDate> commonDate = HolidayUtils.findFirstCommonDate(country1Holidays, country2Holidays);

		if (commonDate.isPresent()) {
			CommonHoliday holidayInfo = new CommonHoliday(commonDate.get(),
					country1Holidays.stream().filter(x -> x.getDate().equals(commonDate.get())).findFirst().get()
							.getName(),
					country2Holidays.stream().filter(x -> x.getDate().equals(commonDate.get())).findFirst().get()
							.getName());
			return Optional.of(holidayInfo);

		}
		return Optional.empty();
	}

}
