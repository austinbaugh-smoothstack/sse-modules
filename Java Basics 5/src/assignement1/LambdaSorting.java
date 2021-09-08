package assignement1;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class LambdaSorting {
	private static void print(final String label, final String strings[]) {
		System.out.println(label + ":");
		System.out.println(" " + Arrays.toString(strings));
	}
	
	private static int countChar(final String s1, final String s2, final char c) {
		for(int i = 0; i < s1.length(); i++) {
			if(s1.charAt(i) == 'e') {
				return 0;
			}
		}
		return 1;
	}
	
	private static void step1() {
		String[] strings = {
			"music",
			"eating",
			"something",
			"cats"
		};
	
		LambdaSorting.print("Original list", strings);
		
		Arrays.sort(strings, (s1, s2) -> s1.length() - s2.length());
		LambdaSorting.print("Sorted By Length", strings);
		
		Arrays.sort(strings, (s1, s2) -> s2.length() - s1.length());
		LambdaSorting.print("Sorted By Reverse Length", strings);
		
		Arrays.sort(strings, (s1, s2) -> s1.charAt(0) - s2.charAt(0));
		LambdaSorting.print("Sorted Alphabetically by first char only", strings);
		
		Arrays.sort(strings, (s1, s2) -> {
			for(int i = 0; i < s1.length(); i++) {
				if(s1.charAt(i) == 'e') {
					return -1;
				}
			}
			return 1;
		});
		LambdaSorting.print("Sorted based on strings with the letter 'e'", strings);
		
		Arrays.sort(strings, (s1, s2) -> LambdaSorting.countChar(s1, s2, 'e'));
		LambdaSorting.print("Sorted based on strings with the letter 'e'", strings);
	}
	
	private static List<String> step2(List<Integer> numbers) {
		return numbers
			.stream()
			.map(num -> (num % 2 == 0 ? "e" : "o") + num)
			.collect(Collectors.toList());
	}
	
	private static List<String> step3(List<String> strings) {
		return strings
			.stream()
			.filter(s -> s.charAt(0) == 'a' && s.length() == 3)
			.collect(Collectors.toList());
	}
	
	public static void main(final String[] _args) {
		System.out.println("Step 1)");
		LambdaSorting.step1();
		System.out.println();
		
		System.out.println("Step 2)");
		final List<Integer> numbers = Arrays.asList(10, 33);
		final String numStrings[] = LambdaSorting.step2(numbers).stream().toArray(String[]::new);
		LambdaSorting.print("Evens and odds", numStrings);
		System.out.println();
		
		System.out.println("Step 3)");
		final List<String> strings = Arrays.asList("aaa", "baa", "abab", "bba", "aaa", "bbbb");
		final String filteredStrings[] = LambdaSorting.step3(strings).stream().toArray(String[]::new);
		LambdaSorting.print("Filtered strings", filteredStrings);
		System.out.println();
	}
}