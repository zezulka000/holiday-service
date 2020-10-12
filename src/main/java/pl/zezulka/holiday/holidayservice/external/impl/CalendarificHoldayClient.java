/**
 * 
 */
package pl.zezulka.holiday.holidayservice.external.impl;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import pl.zezulka.holiday.holidayservice.external.IHolidayClient;
import pl.zezulka.holiday.holidayservice.external.dto.CalendarificCountry;
import pl.zezulka.holiday.holidayservice.external.dto.CalendarificHoliday;
import pl.zezulka.holiday.holidayservice.external.exception.HolidayClientException;
import pl.zezulka.holiday.holidayservice.model.Country;
import pl.zezulka.holiday.holidayservice.model.Holiday;

/**
 * @author ania
 *
 */
@Component
public class CalendarificHoldayClient implements IHolidayClient {

	private static final Logger LOGGER = LoggerFactory.getLogger(CalendarificHoldayClient.class);

	@Value("${calendarific.apiKey}")
	private String apiKey;
	@Value("${calendarific.endpoint}")
	private String endpoint;

	private RestTemplate restTemplate;

	public CalendarificHoldayClient() {
		restTemplate = new RestTemplate();
	}

	@Override
	public List<Country> getSupportedCountries() throws HolidayClientException {

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.add("user-agent", "Application");

		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(endpoint).path("countries")
				.queryParam("api_key", apiKey);

		HttpEntity<String> entity = new HttpEntity<String>(headers);

		ResponseEntity<CalendarificCountry> responseEntity = null;
		try {
			responseEntity = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity,
					CalendarificCountry.class);
		} catch (HttpStatusCodeException e) {
			throw new HolidayClientException(e.getMessage(), e);
		} catch (RestClientException e) {
			throw new HolidayClientException(e.getMessage(), e);
		}

		CalendarificCountry body = responseEntity.getBody();

		List<Country> result = body.getResponse().getCountries().stream()
				.map((CalendarificCountry.Country c) -> new Country(c.getIso3166())).collect(Collectors.toList());

		return result;
	}

	@Override
	public List<Holiday> getHolidays(String countryCode, int year) throws HolidayClientException {

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.add("user-agent", "Application");

		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(endpoint).path("holidays")
				.queryParam("api_key", apiKey).queryParam("country", countryCode).queryParam("year", year);

		HttpEntity<String> entity = new HttpEntity<String>(headers);

		ResponseEntity<CalendarificHoliday> responseEntity = null;
		try {
			responseEntity = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity,
					CalendarificHoliday.class);
		} catch (HttpStatusCodeException e) {
			LOGGER.error(e.getMessage(), e);
			throw new HolidayClientException(e.getMessage(), e);
		} catch (RestClientException e) {
			LOGGER.error(e.getMessage(), e);
			throw new HolidayClientException(e.getMessage(), e);
		}

		CalendarificHoliday body = responseEntity.getBody();

		List<Holiday> result = body.getResponse().getHolidays().stream()
				.map((CalendarificHoliday.Holiday h) -> new Holiday(h.getCountry().getId(), h.getName(),
						LocalDate.of(h.getDate().getDatetime().getYear(), h.getDate().getDatetime().getMonth(),
								h.getDate().getDatetime().getDay())))
				.collect(Collectors.toList());

		return result;

	}

}
