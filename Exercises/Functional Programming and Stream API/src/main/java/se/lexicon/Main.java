package se.lexicon;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Main {

	@FunctionalInterface
	interface PersonRule {
		boolean test(Person person);
	}

	@FunctionalInterface
	interface PersonAction {
		void execute(Person person);
	}

	public static List<Person> filterPeople(List<Person> list, PersonRule rule) {
		List<Person> result = new ArrayList<>();
		for (Person p : list) {
			if (rule.test(p)) {
				result.add(p);
			}
		}
		return result;
	}

	static class PersonProcessor {
		public static List<Person> findPeople(List<Person> list, Predicate<Person> rule) {
			List<Person> result = new ArrayList<>();
			for (Person p : list) {
				if (rule.test(p)) {
					result.add(p);
				}
			}
			return result;
		}

		public static void applyToMatching(List<Person> list, Predicate<Person> rule, Consumer<Person> action) {
			for (Person p : list) {
				if (rule.test(p)) {
					action.accept(p);
				}
			}
		}
	}

	public static void main(String[] args) {
		List<Person> people = List.of(
				new Person("Amina", 22, "Stockholm", true),
				new Person("Erik", 17, "Uppsala", true),
				new Person("Noah", 34, "Stockholm", false),
				new Person("Sara", 29, "Gothenburg", true),
				new Person("Lina", 41, "Malmö", false),
				new Person("Omar", 19, "Stockholm", true)
		);

		System.out.println("--- Core Tasks ---Part 1");

		PersonRule activeRule = person -> person.isActive();
		PersonRule adultRule = person -> person.getAge() >= 18;
		PersonRule stockholmRule = person -> "Stockholm".equalsIgnoreCase(person.getCity());

		PersonAction printName = person -> System.out.println(person.getName());
		PersonAction sendEmail = person -> System.out.println("Send email to " + person.getName());

		// Testing custom filter
		List<Person> stockholmResidents = filterPeople(people, stockholmRule);
		System.out.println("Stockholm Residents:");
		for (Person p : stockholmResidents) {
			printName.execute(p);
		}

		System.out.println("\n--- Optional Tasks (Composition & Predicate/Consumer) ---");

		Predicate<Person> isActive = Person::isActive;
		Predicate<Person> isAdult = p -> p.getAge() >= 18;
		Predicate<Person> livesInStockholm = p -> "Stockholm".equalsIgnoreCase(p.getCity());

		Predicate<Person> activeAndAdult = isActive.and(isAdult);
		Predicate<Person> adultOrLivesInStockholm = isAdult.or(livesInStockholm);
		Predicate<Person> notActive = isActive.negate();

		Consumer<Person> emailConsumer = p -> System.out.println("Send email to " + p.getName());

		System.out.println("Active and Adult individuals:");
		List<Person> filtered = PersonProcessor.findPeople(people, activeAndAdult);
		filtered.forEach(System.out::println);

		System.out.println("\nSending emails to adults or Stockholm residents:");
		PersonProcessor.applyToMatching(people, adultOrLivesInStockholm, emailConsumer);

		System.out.println("--- Core Tasks ---Part 2");
		List<Person> activePeople = people.stream()
				.filter(Person::isActive)
				.collect(Collectors.toList());
		System.out.println("1. Active People: " + activePeople);

		List<String> allNames = people.stream()
				.map(Person::getName)
				.collect(Collectors.toList());
		System.out.println("2. All Names: " + allNames);

		long adultCount = people.stream()
				.filter(p -> p.getAge() >= 18)
				.count();
		System.out.println("3. Count of Adults: " + adultCount);

		List<Person> sortedByAge = people.stream()
				.sorted(Comparator.comparingInt(Person::getAge))
				.collect(Collectors.toList());
		System.out.println("4. Sorted by Age: " + sortedByAge);

		Optional<Person> firstActiveInStockholm = people.stream()
				.filter(p -> "Stockholm".equalsIgnoreCase(p.getCity()))
				.filter(Person::isActive)
				.findFirst();

		System.out.print("5. First active person in Stockholm: ");
		firstActiveInStockholm.ifPresentOrElse(
				p -> System.out.println(p.getName()),
				() -> System.out.println("None found")
		);
		System.out.println("\n--- Optional Tasks (Multiple Filters) ---");
		List<Person> activeAdults = people.stream()
				.filter(Person::isActive)
				.filter(p -> p.getAge() >= 18)
				.collect(Collectors.toList());
		System.out.println("Active adults: " + activeAdults);
		List<Person> activeInStockholm = people.stream()
				.filter(Person::isActive)
				.filter(p -> "Stockholm".equalsIgnoreCase(p.getCity()))
				.collect(Collectors.toList());
		System.out.println("Active in Stockholm: " + activeInStockholm);
		List<Person> inactiveOlderThan30 = people.stream()
				.filter(p -> !p.isActive())
				.filter(p -> p.getAge() > 30)
				.collect(Collectors.toList());
		System.out.println("Inactive older than 30: " + inactiveOlderThan30);

		System.out.println("\n--- Optional Tasks (Distinct and Sorting) ---");
		List<String> uniqueSortedCities = people.stream()
				.map(Person::getCity)
				.distinct()
				.sorted()
				.collect(Collectors.toList());
		System.out.println("Unique sorted cities: " + uniqueSortedCities);

		List<String> uniqueFirstLetters = people.stream()
				.map(p -> p.getName().substring(0, 1))
				.distinct()
				.sorted()
				.collect(Collectors.toList());
		System.out.println("Unique sorted first letters: " + uniqueFirstLetters);

		System.out.println("\n--- Optional Tasks (Mapping and Formatting) ---");
		List<String> formattedProfiles = people.stream()
				.map(p -> p.getName() + " (" + p.getAge() + ") - " + p.getCity())
				.collect(Collectors.toList());
		System.out.println("Formatted strings:");
		formattedProfiles.forEach(System.out::println);

		List<String> generatedEmails = people.stream()
				.map(p -> p.getName().toLowerCase() + "@example.com")
				.collect(Collectors.toList());
		System.out.println("\nGenerated emails: " + generatedEmails);
	}
}

