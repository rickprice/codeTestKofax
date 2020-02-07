package ca.pricemail.rprice;

import java.util.Arrays;
import java.util.List;

public class SchoolGradeRecord {
	// A list of the grade values we consider valid
	static final List<String> validGrades = Arrays.asList("K", "1", "2", "3", "4", "5", "6", "7", "8");

	// We are essentially make a Case class here...
	private final String province;
	private final String city;
	private final String school;
	private final String grade;
	private final long population;

	// The constructor for the class
	// We assert that various invariants hold as well so we can just trust the data and use the class as read/only
	// We assume that Java will throw exceptions for out of bounds array accesses etc, for this example we don't wrap
	// them in more helpful exceptions
	public SchoolGradeRecord(String incomingRecordString) {
		String[] columns = incomingRecordString.split("\t");

		// We need at least 4 values, or there is something wrong...
		System.out.println("Verifying we have at least 4 tab separated columns");
		assert (columns.length >= 4);

		System.out.println("Starting to assign split columns to variables");
		this.province = columns[0];
		this.city = columns[1];
		this.school = columns[2];
		this.grade = columns[3].substring(0, 1);
		this.population = Long.parseLong(columns[3].substring(1));

		System.out.println("Asserting invariants about incoming data");
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
