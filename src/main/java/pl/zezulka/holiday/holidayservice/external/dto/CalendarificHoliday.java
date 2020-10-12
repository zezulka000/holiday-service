/**
 * 
 */
package pl.zezulka.holiday.holidayservice.external.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author ania
 *
 */
public class CalendarificHoliday {

	private Meta meta;
	private Response response;

	@JsonProperty("meta")
	public Meta getMeta() {
		return this.meta;
	}

	public void setMeta(Meta meta) {
		this.meta = meta;
	}

	@JsonProperty("response")
	public Response getResponse() {
		return this.response;
	}

	public void setResponse(Response response) {
		this.response = response;
	}

	public static class Meta {
		private int code;

		@JsonProperty("code")
		public int getCode() {
			return this.code;
		}

		public void setCode(int code) {
			this.code = code;
		}

	}

	public static class Country {
		private String id;
		private String name;

		@JsonProperty("id")
		public String getId() {
			return this.id;
		}

		public void setId(String id) {
			this.id = id;
		}

		@JsonProperty("name")
		public String getName() {
			return this.name;
		}

		public void setName(String name) {
			this.name = name;
		}

	}

	public static class Datetime {
		private int year;
		private int month;
		private int day;
		private int hour;
		private int minute;
		private int second;

		@JsonProperty("year")
		public int getYear() {
			return this.year;
		}

		public void setYear(int year) {
			this.year = year;
		}

		@JsonProperty("month")
		public int getMonth() {
			return this.month;
		}

		public void setMonth(int month) {
			this.month = month;
		}

		@JsonProperty("day")
		public int getDay() {
			return this.day;
		}

		public void setDay(int day) {
			this.day = day;
		}

		@JsonProperty("hour")
		public int getHour() {
			return this.hour;
		}

		public void setHour(int hour) {
			this.hour = hour;
		}

		@JsonProperty("minute")
		public int getMinute() {
			return this.minute;
		}

		public void setMinute(int minute) {
			this.minute = minute;
		}

		@JsonProperty("second")
		public int getSecond() {
			return this.second;
		}

		public void setSecond(int second) {
			this.second = second;
		}

	}

	public static class Timezone {
		private String offset;
		private String zoneabb;
		private int zoneoffset;
		private int zonedst;
		private int zonetotaloffset;

		@JsonProperty("offset")
		public String getOffset() {
			return this.offset;
		}

		public void setOffset(String offset) {
			this.offset = offset;
		}

		@JsonProperty("zoneabb")
		public String getZoneabb() {
			return this.zoneabb;
		}

		public void setZoneabb(String zoneabb) {
			this.zoneabb = zoneabb;
		}

		@JsonProperty("zoneoffset")
		public int getZoneoffset() {
			return this.zoneoffset;
		}

		public void setZoneoffset(int zoneoffset) {
			this.zoneoffset = zoneoffset;
		}

		@JsonProperty("zonedst")
		public int getZonedst() {
			return this.zonedst;
		}

		public void setZonedst(int zonedst) {
			this.zonedst = zonedst;
		}

		@JsonProperty("zonetotaloffset")
		public int getZonetotaloffset() {
			return this.zonetotaloffset;
		}

		public void setZonetotaloffset(int zonetotaloffset) {
			this.zonetotaloffset = zonetotaloffset;
		}

	}

	public static class Date {
		private Object iso;
		private Datetime datetime;
		private Timezone timezone;

		@JsonProperty("iso")
		public Object getIso() {
			return this.iso;
		}

		public void setIso(Object iso) {
			this.iso = iso;
		}

		@JsonProperty("datetime")
		public Datetime getDatetime() {
			return this.datetime;
		}

		public void setDatetime(Datetime datetime) {
			this.datetime = datetime;
		}

		@JsonProperty("timezone")
		public Timezone getTimezone() {
			return this.timezone;
		}

		public void setTimezone(Timezone timezone) {
			this.timezone = timezone;
		}

	}

	public static class Holiday {
		private String name;
		private String description;
		private Country country;
		private Date date;
		private List<String> type;
		private String locations;
		private Object states;

		@JsonProperty("name")
		public String getName() {
			return this.name;
		}

		public void setName(String name) {
			this.name = name;
		}

		@JsonProperty("description")
		public String getDescription() {
			return this.description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		@JsonProperty("country")
		public Country getCountry() {
			return this.country;
		}

		public void setCountry(Country country) {
			this.country = country;
		}

		@JsonProperty("date")
		public Date getDate() {
			return this.date;
		}

		public void setDate(Date date) {
			this.date = date;
		}

		@JsonProperty("type")
		public List<String> getType() {
			return this.type;
		}

		public void setType(List<String> type) {
			this.type = type;
		}

		@JsonProperty("locations")
		public String getLocations() {
			return this.locations;
		}

		public void setLocations(String locations) {
			this.locations = locations;
		}

		@JsonProperty("states")
		public Object getStates() {
			return this.states;
		}

		public void setStates(Object states) {
			this.states = states;
		}

	}

	public static class Response {
		private List<Holiday> holidays;

		@JsonProperty("holidays")
		public List<Holiday> getHolidays() {
			return this.holidays;
		}

		public void setHolidays(List<Holiday> holidays) {
			this.holidays = holidays;
		}

	}

}
