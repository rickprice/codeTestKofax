package ca.pricemail.rprice;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SchoolGradeRollup {
	public static List<Pair<String, Long>> getRollupFromStream(Stream<String> stream) {
		List<Pair<String, Long>> returnValue = new ArrayList<>();

		Map<String, Map<String, Map<String, Long>>> g = stream.map(line -> new SchoolGradeRecord(line))
				.collect(Collectors.groupingBy(SchoolGradeRecord::getProvince, TreeMap::new,
						Collectors.groupingBy(SchoolGradeRecord::getCity, TreeMap::new,
								Collectors.groupingBy(SchoolGradeRecord::getSchool, TreeMap::new,
										Collectors.summingLong(SchoolGradeRecord::getPopulation)))));

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