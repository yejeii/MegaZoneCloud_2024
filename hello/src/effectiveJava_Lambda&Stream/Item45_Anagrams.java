

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Item 45
 * 
 * 스트림을 제대로 사용하면 프로그램이 짧고 간결해지지만, 잘못 사용하면 읽기 어렵고 유지보수도 어려워진다.
 */
public class Item45_Anagrams {

	public static void ex1(String[] args) throws IOException {
		File dictionary = new File(args[0]);
		int minGroupSize = Integer.parseInt(args[1]);
		
		Map<String, Set<String>> groups = new HashMap<String, Set<String>>();
		try(Scanner s = new Scanner(dictionary)) {
			while(s.hasNext()) {
				String word = s.next();
				
				/* 
				 * computeIfAbsent(String key, Function<? super String, ? extends Set<String>> mappingFunction)
				 * 	- key 가 존재할 경우 : 아무런 작업하지 않고 기존에 존재하는 Key 의 Value(여기선 Set<String>) 리턴
				 *  - key 존재 안하는 경우 : 람다식을 적용한 값(새로운 값)을 해당 key 에 저장한 후, 새로운 값 리턴
				 */
//				groups.computeIfAbsent(alphabetize(word), 
//										new Function<String, Set<String>>() {
//											@Override
//											public Set<String> apply(String unused) {
//												return new TreeSet<String>();
//											}
//										})
//					.add(word);
				
				groups.computeIfAbsent(alphabetize(word), (unused) -> new TreeSet<>()).add(word);
				
			}
		}
		
		for(Set<String> group : groups.values()) {
			if(group.size() >= minGroupSize) {
				System.out.println(group.size() + " : " + group);
			}
		}
		
	}
	
	// 스트림과 반복문 적절히 조합
	public static void main(String[] args) throws IOException {
		Path dictionary = Paths.get(args[0]);
		int minGroupSize = Integer.parseInt(args[1]);
		
		/* 
		 * 1. try-catch-resources 블록에서 사전 파일을 열고, 파일의 모든 라인으로 구성된 Stream 을 얻는다.
		 * 2. 해당 스트림의 파이프라인에는 중간 연산은 없으며, 종단 연산(collect)에서 모든 단어를 수집해 Map 으로 모은다.
		 * 		Collectors.groupingBy(Function<? super T, ? extends K> classifier)
		 * 			: T를 K로 매핑한 후, 키가 K이면서 T를 저장하는 요소를 값으로 갖는 Map 생성, 리턴
		 * 3. 리턴된 Map 의 values() 가 반환한 값으로부터 새로운 Stream<List<String>> 스트림을 연다.
		 * 4. 리스트들 중 원소가 minGroupSize 보다 적은 것은 필터링한다.
		 * 5. 종단연산인 forEach() 은 살아남은 리스트를 출력한다.
		 */
		try(Stream<String> words = Files.lines(dictionary)) {
			words.collect(Collectors.groupingBy(word -> alphabetize(word)))
				.values().stream()
				.filter(group -> group.size() >= minGroupSize)
				.forEach(group -> System.out.println(group.size() + " : " + group));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	private static String alphabetize(String s) {
		char[] a = s.toCharArray();
		Arrays.sort(a);
		return new String(a);
	}

}
