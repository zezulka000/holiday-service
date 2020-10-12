/**
 * 
 */
package pl.zezulka.holiday.holidayservice.model;

import java.time.LocalDate;

/**
 * @author ania
 *
 */
public class Holiday {

	private final String countryCode;
	private final String name;
	private final LocalDate date;

	public Holiday(String countryCode, String name, LocalDate date) {
		super();
		this.countryCode = countryCode;
		this.name = name;
		this.date = date;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public String getName() {
		return name;
	}

	public LocalDate getDate() {
		return date;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((countryCode == null) ? 0 : countryCode.hashCode());
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Holiday other = (Holiday) obj;
		if (countryCode == null) {
			if (other.countryCode != null)
				return false;
		} else if (!countryCode.equals(other.countryCode))
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Holiday [countryCode=" + countryCode + ", name=" + name + ", date=" + date + "]";
	}

}
