/**
 * 
 */
package pl.zezulka.holiday.holidayservice.controller;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import pl.zezulka.holiday.holidayservice.model.CommonHoliday;
import pl.zezulka.holiday.holidayservice.model.Country;
import pl.zezulka.holiday.holidayservice.model.Holiday;
import pl.zezulka.holiday.holidayservice.service.IHolidayService;

/**
 * @author ania
 *
 */
@ExtendWith(MockitoExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class HolidayControllerTest {

	@Autowired
	private MockMvc mvc;

	@Mock
	private IHolidayService holidayService;

	@InjectMocks
	private HolidayController holidayController;

	private Country pl = new Country("PL");
	private Country de = new Country("DE");

	private Holiday christmasPL = new Holiday("PL", "pl christmas", LocalDate.of(2020, 12, 25));
	private Holiday newYearPL = new Holiday("PL", "pl new year", LocalDate.of(2021, 1, 1));
	private Holiday easterPL = new Holiday("PL", "pl easter", LocalDate.of(2021, 4, 4));

	private Holiday allSaintsDE = new Holiday("DE", "de all saints", LocalDate.of(2020, 11, 1));
	private Holiday christmasDE = new Holiday("DE", "de christmas", LocalDate.of(2020, 12, 25));
	private Holiday newYearDE = new Holiday("DE", "de new year", LocalDate.of(2021, 1, 1));

	@BeforeEach
	void setUp() {
		MockitoAnnotations.initMocks(this);
		this.mvc = MockMvcBuilders.standaloneSetup(holidayController).build();
	}

	@Test
	public void getHolidayRequiredParameterTest() throws Exception {

		mvc.perform(MockMvcRequestBuilders.get("/holiday").accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isBadRequest()).andReturn();

	}

	@Test
	public void getHolidayRequiredParameterWrongDateTest() throws Exception {

		MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
		parameters.put("date", Collections.singletonList("2020-10-10x"));
		parameters.put("countryCode1", Collections.singletonList("PL"));
		parameters.put("countryCode2", Collections.singletonList("DE"));

		mvc.perform(MockMvcRequestBuilders.get("/holiday").params(parameters).accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isBadRequest()).andReturn();

	}

	@Test
	public void whenCountryIsNotSupportedReturn404() throws Exception {

		Mockito.when(holidayService.getSupportedCountries()).thenReturn(Arrays.asList(pl, de));

		Assertions.assertTrue(holidayService.getSupportedCountries().size() == 2, "size should be 2");

		MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
		parameters.put("date", Collections.singletonList("2020-10-10"));
		parameters.put("countryCode1", Collections.singletonList("XX"));
		parameters.put("countryCode2", Collections.singletonList("PL"));

		mvc.perform(MockMvcRequestBuilders.get("/holiday").params(parameters).accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isNotFound()).andReturn();

	}

	@Test
	public void whenCommonHolidayIsNotFoundReturn404() throws Exception {

		Mockito.when(holidayService.getSupportedCountries()).thenReturn(Arrays.asList(pl, de));

		Assertions.assertTrue(holidayService.getSupportedCountries().size() == 2, "size should be 2");

		Mockito.when(holidayService.getUpcommingHolidays(pl.getCode(), LocalDate.of(2020, 10, 10)))
				.thenReturn(Arrays.asList(newYearPL));

		Mockito.when(holidayService.getUpcommingHolidays(de.getCode(), LocalDate.of(2020, 10, 10)))
				.thenReturn(Arrays.asList(christmasDE));

		MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
		parameters.put("date", Collections.singletonList("2020-10-10"));
		parameters.put("countryCode1", Collections.singletonList("DE"));
		parameters.put("countryCode2", Collections.singletonList("PL"));

		mvc.perform(MockMvcRequestBuilders.get("/holiday").params(parameters).accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isNotFound()).andReturn();

	}

	@Test
	public void whenCommonHolidayIsFoundReturn200() throws Exception {

		Mockito.when(holidayService.getSupportedCountries()).thenReturn(Arrays.asList(pl, de));

		Assertions.assertTrue(holidayService.getSupportedCountries().size() == 2, "size should be 2");

		List<Holiday> plHolidays = Arrays.asList(christmasPL, newYearPL, easterPL);
		List<Holiday> deHolidays = Arrays.asList(allSaintsDE, christmasDE, newYearDE);
		Mockito.when(holidayService.getUpcommingHolidays(pl.getCode(), LocalDate.of(2020, 10, 10)))
				.thenReturn(plHolidays);

		Mockito.when(holidayService.getUpcommingHolidays(de.getCode(), LocalDate.of(2020, 10, 10)))
				.thenReturn(deHolidays);

		Mockito.when(holidayService.getFirstCommonHoliday(deHolidays, plHolidays)).thenReturn(
				Optional.of(new CommonHoliday(christmasDE.getDate(), christmasDE.getName(), christmasPL.getName())));

		MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
		parameters.put("date", Collections.singletonList("2020-10-10"));
		parameters.put("countryCode1", Collections.singletonList("DE"));
		parameters.put("countryCode2", Collections.singletonList("PL"));

		MvcResult result = mvc
				.perform(MockMvcRequestBuilders.get("/holiday").params(parameters).accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

		String resultContent = result.getResponse().getContentAsString();
		Assertions.assertNotNull(resultContent);

	}

}
