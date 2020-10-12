/**
 * 
 */
package pl.zezulka.holiday.holidayservice.model;

import java.time.LocalDate;

/**
 * @author ania
 *
 */
public class CommonHoliday {

	private LocalDate date;
	private String name1;
	private String name2;

	public CommonHoliday() {

	}

	public CommonHoliday(LocalDate date, String name1, String name2) {
		super();
		this.date = date;
		this.name1 = name1;
		this.name2 = name2;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getName1() {
		return name1;
	}

	public void setName1(String name1) {
		this.name1 = name1;
	}

	public String getName2() {
		return name2;
	}

	public void setName2(String name2) {
		this.name2 = name2;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((name1 == null) ? 0 : name1.hashCode());
		result = prime * result + ((name2 == null) ? 0 : name2.hashCode());
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
		CommonHoliday other = (CommonHoliday) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (name1 == null) {
			if (other.name1 != null)
				return false;
		} else if (!name1.equals(other.name1))
			return false;
		if (name2 == null) {
			if (other.name2 != null)
				return false;
		} else if (!name2.equals(other.name2))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "HolidayInfo [date=" + date + ", name1=" + name1 + ", name2=" + name2 + "]";
	}

}
