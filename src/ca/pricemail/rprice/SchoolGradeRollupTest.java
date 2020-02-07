package ca.pricemail.rprice;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

class SchoolGradeRollupTest {

	@Test
	void testGetRollupFromStream() {
		// This is the test data given in the quiz document
		String[] inputArray = {
				"ON\tWaterloo\tSchool1\tK1000",
				"ON\tWaterloo\tSchool1\t12000",
				"ON\tWaterloo\tSchool2\t83000",
				"ON\tKitchener\tSchool3\tK0043",
				"ON\tKitchener\tSchool3\t10057",
				"ON\tKitchener\tSchool3\t20003",
				"ON\tKitchener\tSchool3\t30005",
				"ON\tKitchener\tSchool3\t40000",
				};
		
		// These are the quiz results given in the quiz document
		List<Pair<String, Long>> correctResultList = Arrays.asList(
				Pair.of("School3", 108l),
				Pair.of("Kitchener", 108l),
				Pair.of("School1", 3000l),
				Pair.of("School2", 3000l),
				Pair.of("Waterloo", 6000l),
				Pair.of("ON", 6108l)
		);
		
		// Run the rollup using a String stream...
		List<Pair<String, Long>> returnValue = SchoolGradeRollup.getRollupFromStream(Arrays.stream(inputArray));
		
		assertEquals(correctResultList,returnValue);
	}

}
