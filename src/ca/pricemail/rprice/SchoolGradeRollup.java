package ca.pricemail.rprice;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SchoolGradeRollup {
	// This function returns pairs of Strings/Numbers that represent the rollup of the school population
	// data. The String part would be a Province, City or School, and the Number would be the count of 
	// the students for that grouping.
	public static List<Pair<String, Long>> getRollupFromStream(Stream<String> stream) {
		List<Pair<String, Long>> returnValue = new ArrayList<>();

		// Using Java8 streams API to organize the data, we specify the TreeMap specially to have it
		// sort things for us; normally it would use a regular map, and our data wouldn't be sorted...
		// We group by Province, then City, then School
		System.out.println("Organizing school data into buckets");
		Map<String, Map<String, Map<String, Long>>> g = stream.map(line -> new SchoolGradeRecord(line))
				.collect(Collectors.groupingBy(SchoolGradeRecord::getProvince, TreeMap::new,
						Collectors.groupingBy(SchoolGradeRecord::getCity, TreeMap::new,
								Collectors.groupingBy(SchoolGradeRecord::getSchool, TreeMap::new,
										Collectors.summingLong(SchoolGradeRecord::getPopulation)))));

		// I would have used the Java8 Streams forEach, but you can't update variables outside of a Lambda in
		// Java. This means I wasn't able to update the sums. Because of this I'm using a plain loop for that.
		// I might have been able to create a custom collector, but that was too much work given the timeframe.
		// sump is the sum of the school population by province
		// sumc is the sum of the school population by city
		// entryS.getValue() is the sum of the school population by school
		System.out.println("Calculating totals and creating rollup");
		for (Map.Entry<String, Map<String, Map<String, Long>>> entryP : g.entrySet()) {
			long sump = 0;
			for (Entry<String, Map<String, Long>> entryC : entryP.getValue().entrySet()) {
				long sumc = 0;
				for (Entry<String, Long> entryS : entryC.getValue().entrySet()) {
					returnValue.add(Pair.of(entryS.getKey(), entryS.getValue()));
					sumc += entryS.getValue();
				}
				returnValue.add(Pair.of(entryC.getKey(), sumc));
				sump += sumc;
			}
			returnValue.add(Pair.of(entryP.getKey(), sump));
		}

		return returnValue;
	}
}
