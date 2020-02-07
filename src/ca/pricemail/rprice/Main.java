package ca.pricemail.rprice;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class Main {

	public static void main(String[] args) {

		final String fileName = args[1];
		
		System.out.printf("Reading in file: %s%n",fileName);

		// read file into stream, try-with-resources
		try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
			// Directly print out the returned value using forEach
			// It does not match the formatting of the quiz document exactly, but
			// has the correct data. If I had to have the format be exactly the same,
			// I would probably use a printf with a fixed width string for the left
			// hand side.
			SchoolGradeRollup.getRollupFromStream(stream).forEach(System.out::println);
		} catch (IOException e) {
			System.out.println("Had a problem reading file, stack trace follows");
			e.printStackTrace();
		}
	}
}
