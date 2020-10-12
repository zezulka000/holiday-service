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
public class CalendarificCountry {

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

		private String uuid;
		private String country_name;
		private String iso3166;
		private int total_holidays;
		private int supported_languages;

		@JsonProperty("country_name")
		public String getCountry_name() {
			return this.country_name;
		}

		public void setCountry_name(String country_name) {
			this.country_name = country_name;
		}

		@JsonProperty("iso-3166")
		public String getIso3166() {
			return this.iso3166;
		}

		public void setIso3166(String iso3166) {
			this.iso3166 = iso3166;
		}

		@JsonProperty("total_holidays")
		public int getTotal_holidays() {
			return this.total_holidays;
		}

		public void setTotal_holidays(int total_holidays) {
			this.total_holidays = total_holidays;
		}

		@JsonProperty("supported_languages")
		public int getSupported_languages() {
			return this.supported_languages;
		}

		public void setSupported_languages(int supported_languages) {
			this.supported_languages = supported_languages;
		}

		@JsonProperty("uuid")
		public String getUuid() {
			return this.uuid;
		}

		public void setUuid(String uuid) {
			this.uuid = uuid;
		}

	}

	public static class Response {
		private String url;
		private List<Country> countries;

		@JsonProperty("url")
		public String getUrl() {
			return this.url;
		}

		public void setUrl(String url) {
			this.url = url;
		}

		@JsonProperty("countries")
		public List<Country> getCountries() {
			return this.countries;
		}

		public void setCountries(List<Country> countries) {
			this.countries = countries;
		}

	}

}
