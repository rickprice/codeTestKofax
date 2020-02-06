package ca.pricemail.rprice;

import java.util.Arrays;
import java.util.List;

public class SchoolGradeRecord {
	static final List<String> validGrades = Arrays.asList("K", "1", "2", "3", "4", "5", "6", "7", "8");

	private final String province;
	private final String city;
	private final String school;
	private final String grade;
	private final long population;

	public SchoolGradeRecord(String incomingRecordString) {
		String[] columns = incomingRecordString.split("\t");

		// We need at least 4 values, or there is something wrong...
		assert (columns.length >= 4);

		this.province = columns[0];
		this.city = columns[1];
		this.school = columns[2];
		this.grade = columns[3].substring(0, 1);
		this.population = Long.parseLong(columns[3].substring(1));

		assert (validGrades.contains(grade));
		assert (population >= 0);
	}

	public String getProvince() {
		return province;
	}

	public String getCity() {
		return city;
	}

	public String getSchool() {
		return school;
	}

	public String getGrade() {
		return grade;
	}

	public long getPopulation() {
		return population;
	}

}
