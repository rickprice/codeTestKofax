package ca.pricemail.rprice;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class SchoolGradeRecordTest {

	@Test
	void testSchoolGradeRecord1() {
		SchoolGradeRecord r = new SchoolGradeRecord("ON\tWaterloo\tSchool1\tK1000");

		// Make sure a valid record gets cracked correctly
		assertEquals("ON", r.getProvince());
		assertEquals("Waterloo", r.getCity());
		assertEquals("School1", r.getSchool());
		assertEquals("K", r.getGrade());
		assertEquals(1000, r.getPopulation());
	}

	@Test
	void testSchoolGradeRecord2() {

		// Should blow up with an error because we passed too few fields
		assertThrows(AssertionError.class, () -> {
			new SchoolGradeRecord("ON\tWaterlooSchool1\tK1000");
		});
	}

	@Test
	void testSchoolGradeRecord3() {

		// Should blow up with an error because we passed in an incorrect grade
		assertThrows(AssertionError.class, () -> {
			new SchoolGradeRecord("ON\tWaterloo\tSchool1\t91000");
		});
	}
}
