package ca.pricemail.rprice;

import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

	public static void main(String[] args) {

		final String fileName = args[1];

		// read file into stream, try-with-resources
		try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
			Map<String, Map<String, Map<String, Long>>> g = stream.map(line -> new SchoolGradeRecord(line))
					.collect(Collectors.groupingBy(SchoolGradeRecord::getProvince,
							Collectors.groupingBy(SchoolGradeRecord::getCity,
									Collectors.groupingBy(SchoolGradeRecord::getSchool,
											Collectors.summingLong(SchoolGradeRecord::getPopulation)))));

			System.out.println(g);

			for (Map.Entry<String, Map<String, Map<String, Long>>> entryP : new TreeMap<>(g).entrySet()) {
				long sump = 0;
				for (Entry<String, Map<String, Long>> entryC : new TreeMap<>(entryP.getValue()).entrySet()) {
					long sumc = 0;
					for (Entry<String, Long> entryS : new TreeMap<>(entryC.getValue()).entrySet()) {
						System.out.println(entryS.getKey() + "-----" + entryS.getValue());
						sumc += entryS.getValue();
					}
					System.out.println(entryC.getKey() + "-------" + sumc);
					sump += sumc;
				}
				System.out.println(entryP.getKey() + "-------" + sump);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static <T, K extends Comparable<K>> Collector<T, ?, TreeMap<K, List<T>>> sortedGroupingBy(
			Function<T, K> function) {
		return Collectors.groupingBy(function, TreeMap::new, Collectors.toList());
	}

}
