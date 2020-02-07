package ca.pricemail.rprice;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

	public static void main(String[] args) {

		final String fileName = args[1];

		// read file into stream, try-with-resources
		try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
			SchoolGradeRollup.getRollupFromStream(stream).forEach(System.out::println);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
