/**
 * 
 */
package pl.zezulka.holiday.holidayservice.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pl.zezulka.holiday.holidayservice.exception.CountryCodeNotFoundException;
import pl.zezulka.holiday.holidayservice.exception.HolidayNotFoundException;
import pl.zezulka.holiday.holidayservice.external.exception.HolidayClientException;
import pl.zezulka.holiday.holidayservice.model.CommonHoliday;
import pl.zezulka.holiday.holidayservice.model.Country;
import pl.zezulka.holiday.holidayservice.model.Holiday;
import pl.zezulka.holiday.holidayservice.service.IHolidayService;

/**
 * @author ania
 *
 */
@RestController
@Validated
public class HolidayController {

	private IHolidayService holidayService;

	public HolidayController(IHolidayService holidayService) {
		super();
		this.holidayService = holidayService;
	}

	@GetMapping(path = "/holiday", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CommonHoliday> get(
			@RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
			@RequestParam("countryCode1") String countryCode1, @RequestParam("countryCode2") String countryCode2)
			throws HolidayClientException {

		List<Country> countries = holidayService.getSupportedCountries();

		boolean matchCountryCode1 = countries.stream().anyMatch(t -> t.getCode().equalsIgnoreCase(countryCode1));
		boolean matchCountryCode2 = countries.stream().anyMatch(t -> t.getCode().equalsIgnoreCase(countryCode2));

		if (!matchCountryCode1) {
			throw new CountryCodeNotFoundException(String.format("CountryCode '%s' not supported", countryCode1));
		}
		if (!matchCountryCode2) {
			throw new CountryCodeNotFoundException(String.format("CountryCode '%s' not supported", countryCode2));
		}

		List<Holiday> country1Holidays = holidayService.getUpcommingHolidays(countryCode1, date);
		List<Holiday> country2Holidays = holidayService.getUpcommingHolidays(countryCode2, date);

		Optional<CommonHoliday> commonHoliday = holidayService.getCommonHoliday(country1Holidays, country2Holidays);

		if (commonHoliday.isPresent()) {
			return ResponseEntity.status(HttpStatus.OK).body(commonHoliday.get());
		}

		throw new HolidayNotFoundException("Common holiday not found");

	}

}
