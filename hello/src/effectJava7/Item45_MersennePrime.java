package effectJava7;

import static java.math.BigInteger.*;

import java.math.BigInteger;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

/**
 * Item 45
 * 
 * 처음 20개의 메르센 소수 및 지수를 출력하는 프로그램
 * 메르센 수 : 2^p - 1 형태의 수
 * 여기서 p가 소수이면 해당 메르센 수도 소수일 수 있는데, 이때의 수를 메르센 소수라고 한다. 
 */
public class Item45_MersennePrime {
	
	/*
	 * 모든 소수를 담은 무한 스트림을 반환하는 메서드
	 * 
	 * Stream<T> iterate(final T seed, final UnaryOperator<T> f) 
	 * 	: 람다식으로 매개변수르 받아서, 이 람다식에 의해 계산되는 값들을 요소로 하는 무한 스트림을 생성한다.
	 * 	  seed 로 지정된 값부터 시작해서 람다식 f 에 의해 계산된 결과를 다시 seed 값으로 계산을 반복한다.
	 * 
	 * BigInteger.nextProbablePrime() : 해당 BigInteger보다 큰 가장 첫 번째 소수를 찾는 데 사용
	 * ex. BigInteger a 
     *     		 = new BigInteger("32").nextProbablePrime(); 
     * 	   -> 37
	 */
	public static Stream<BigInteger> primes() {
		
//		return Stream.iterate(TWO, new UnaryOperator<BigInteger>() {
//			@Override
//			public BigInteger apply(BigInteger TWO) {
//				return TWO.nextProbablePrime();
//			}
//		});
		
//		return Stream.iterate(TWO, TWO -> TWO.nextProbablePrime());
		
		return Stream.iterate(TWO, BigInteger::nextProbablePrime);
	}

	/* 
	 * 처음 20개의 메르센 소수 및 지수를 출력
	 * 
	 * BigInteger.pow() : BigInteger 값을 지정된 값의 거듭제곱으로 변환
	 * 		TWO.pow(3) : 2^3
	 * BigInteger.intValueExact() : BigInteger 를 int 로 변환 (형변환한 결과가 int 범위에 속하지 않으면 Exception 발생)
	 * BigInteger.subtract() : 빼기
	 * 
	 * BigInteger.isProbablePrime(c) : 해당 BigInteger 값이 소수이면 true 리턴. 
	 * 								  만약 c 가 0 이하이면 true 리턴
	 * Stream.limit(20) : 20개만큼만 Stream 에서 데이터를 가져와서 새로운 스트림을 생성, 리턴
	 */
	public static void main(String[] args) {
		
//		primes()
//		.map(new Function<BigInteger, BigInteger>() {
//			@Override
//			public BigInteger apply(BigInteger p) {
//				return TWO.pow(p.intValueExact()).subtract(ONE);
//			}
//		})
//		.filter(new Predicate<BigInteger>() {
//			@Override
//			public boolean test(BigInteger mersenne) {
//				return ((BigInteger) mersenne).isProbablePrime(50);
//			}
//		})
//		.limit(20)
//		.forEach(new Consumer<BigInteger>() {
//			@Override
//			public void accept(BigInteger t) {
//				System.out.println(t);
//			}
//		});
		
		primes().map(p -> TWO.pow(p.intValueExact()).subtract(ONE))
			.filter(mersenne -> mersenne.isProbablePrime(50))
			.limit(20)
//			.forEach(System.out::println);
			.forEach(mp -> System.out.println(mp.bitLength() + " : " + mp));
	}
	

		

}
