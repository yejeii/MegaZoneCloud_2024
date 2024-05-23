package ch5;

import java.util.Arrays;

/**
 * 배열
 * - 같은 타입의 여러 변수를 하나의 묶음으로 다루는 것
 * 	 기본형 타입을 저장하는 배열이 있고, 다양한 타입을 저장하는 배열이 있는데 후자를 객체 배열이라고 함
 * - 배열 변수는 참조형 변수로, 배열의 첫 번째 요소의 주소값이 저장되어 있음. 배열의 시작 위치 주소값
 * - 각 요소값을 참조하기 위한 위치 정보가 필요: index
 * - index : 배열의 위치
 * - 배열의 크기 == 배열의 요소 개수
 * - 선언 방법
 * 	 타입[] 배열변수명; (추천)
 * 	 타입 배열변수명[];
 * - 배열 요소 초기화
 * 	 선언과 동시에 초기화 : 배열 크기를 고정
 * 	 선언 후에 초기화 : 요소를 저장하는데 크기를 지정. 필요시 저장
 * - 배열의 복사
 * 	 1. 반복문 활용 : 요소 개수만큼 반복하기에 cost가 상당..
 *   2. System.arraycopy() 메서드 활용
 * - 배열의 활용
 * 	 총합과 평균
 * 	 최대값과 최소값
 *   섞기
 *   빈도수 구하기
 * - 배열 초기화의 기본값
 * 	 boolean			: false
 *   char				: null
 *   byte, short, int	: 0
 *   long				: 0L
 *   float				: 0.0f
 *   double				: 0.0d
 *   참조형				: null (참조형(주소) 배열, 즉 객체 배열)
 *   
 *   >참고<
 *   null : 어떠한 객체도 가리키고 있지 않다는 의미 
 *   		=> 요소 참조시 NullPointerException 발생 => 요소 초기화를 해야 함
 *   >참고<
 *   클래스로 작성한 코드 => 컴파일되어 바이트 코드 => jvm이 바이트 코드를 이용 해 실행 (== 메모리에 클래스가 로딩됨) : 객체(메모리 주소를 가지고 있음)	
 *  
 * - String형
 *   String 클래스는 char 배열에 여러가지 편리한 기능(메서드)를 추가한 타입
 *   String 클래스에 저장된 내용은 읽을 수는 있되, 변경할 수 없다
 *   
 * - 다차원 배열
 * 	 1차원 배열 : 행으로만 구성
 *   2차원 배열 : 행과 열로 구성
 * 
 *   
 */
public class ArrayEx {
	
	public static void main(String[] args) {
		
		int[] score = {40,0,50,90};
//		System.out.println(score[4]);	
		// java.lang.ArrayIndexOutOfBoundsException:
		
		/* 배열 선언 */
		int[] score2;
		int score3[];
		
		int[] iArr1 = new int[10];			// 각 요소를 초기화하지 않은 상태
		int[] iArr2 = {10,20,30,40,50}; 	// 선언과 동시에 초기화
		char[] cArr1 = {'a', 'n', 'c'};
		
		iArr1[0] = 10;
		System.out.println(iArr1[0]);

		iArr1[0] = 20;
		System.out.println(iArr1[0]);
		
		// 반복문을 활용한 전체 요소 초기화
		for (int i = 0; i < iArr1.length; i++) {
			iArr1[i] = i + 1;
			System.out.print(iArr1[i] + " ");
			
		}
		
		System.out.println();
		
		/* 1. 반복문을 활용한 배열복사 */
		int[] arr2 = {1,2,3,4,5};	// 원본배열
		int[] arr2copy = new int[arr2.length];

		for (int i = 0; i < arr2.length; i++) {
			arr2copy[i] = arr2[i];
		}
		
		// >생각해보기< 
		// 복사본이 원본이 되어, 원본이 필요없게 되는 경우 : 가비지 콜렉터 대상.. 
		arr2 = arr2copy;	// arr2에는 기존 배열의 시작주소가 없어지는 대신 복사본 배열의 시작주소가 저장되므로 arr2는 가비지 콜럭테 대상이 됨.
		
		/* 2. System.arraycopy() 
		 * (원본, 원본 시작위치, 복사본, 복사본 시작 위치, 원본 길이) */
		int[] arr3 = {1,2,3,4,5};	// 원본배열
		int[] arr3copy = new int[arr2.length];
		
		System.arraycopy(arr3, 0, arr3copy, 0, arr3.length);
		for (int arr : arr3copy) {
			System.out.print(arr+" ");
		}
		System.out.println();
		
		/* 총합과 평균 */
		int sum = 0;
		float average = 0f;
		
		int[] score4 = {100,80,60,13,55};
		
		for (int i = 0; i < score4.length; i++) {
			sum += score4[i];
		}
		
		average = sum / (float)score4.length;
		System.out.println("sum: "+sum +", average: " + average);
		
		/* 최소값, 최대값 */
		int max = score4[0];
		int min = score4[0];
		
		for (int i = 0; i < score4.length; i++) {
			if(score4[i] > max) {
				max = score4[i];
			} else if (score4[i] < min) {
				min = score4[i];
			}
		}
		System.out.println("max:"+max+", min:"+min);
		
		/* 섞기 - 난수 활용 : 배열 인덱스가 난수로 나와야 함 */
		System.out.println(Math.random());	// double형
		System.out.println((int)(Math.random() * score4.length));	// int로 도출하기 위해 곱한 후 int화 
		
		int[] tmpScore4 = new int[5];
		for (int i = 0; i < tmpScore4.length; i++) {
			int tmpIdx = (int)(Math.random() * score4.length);
			tmpScore4[i] = score4[tmpIdx];
		}
		
		System.out.println(tmpScore4);	// 주소값
		System.out.println(Arrays.toString(tmpScore4));	// 요소값을 String타입으로 출력
		
		// >생각해보기<
		// 반복문... 가독성이 별로
		// -> 자바에서 제공하는 API 사용 : 유지보수 및 코드 품질, 협업에 유용
		// Arrays.toString(tmpScore4);	
		
		/* 빈도수 구하기 
		 * [55, 80, 100, 55, 100]	: 데이터 배열
		 * 카운트 배열 : 해당 데이터 배열의 요소값에 해당하는 위치에 1씩 증가해 저장 */
		int[] numArr = new int[10];		// 데이터 배열
		int[] counter = new int[10];	// 카운트 배열
		
		// 데이터 배열 초기화 - 난수 활용
		for (int i = 0; i < numArr.length; i++) {
			numArr[i] = (int)(Math.random() * 10);
		}
		System.out.println(Arrays.toString(numArr));
		                       
		// 집계
		for (int i = 0; i < numArr.length; i++) {
			// 데이터 배열의 요소값을 집계 배열의 인덱스로 사용
			// numArr : 3,4,5,3,7,8
			counter[numArr[i]]++;
			// counter[3]++;
			// counter[4]++;
			// counter[5]++;
			// counter[3]++;
			// counter[7]++;
			// counter[8]++;
		}
		System.out.println(Arrays.toString(counter));
		
		/* 배열 초기화의 기본값 */
		boolean[] boolArr = new boolean[5];
		String[] strArr = new String[3];
		System.out.println(Arrays.toString(boolArr));
		System.out.println(Arrays.toString(strArr));
				
		/* String 배열 */
		// 1. 선언 후 초기화
		String[] name = new String[3];	// 공간 할당은 됬으나, null로 초기화된 상태
		name[0] = "Kim";
		name[1] = "Park";
		name[2] = "Hong";
		
		// 2. 선언과 동시에 초기화
		String[] name2 = {"Kim", "Park", "Hong"};
		System.out.println(Arrays.toString(name2));
		
		// 2-1. 선언 방법 new String[] 생략 가능
		String[] name3 = new String[] {"Kim", "Park", "Hong"};
		
		/* 참조형 변수 : String 
		 * "KIM"이라는 char 배열을 가지고 있는 변수
		 * >생각해보기<
		 * 문자열을 가공하는 기능이 있었으면 좋겠다고 생각...
		 * char 배열의 활용도를 높이기 위해 만든 자료형이 String형
		 */
		String str = "KIM";
		String str2 = "KIM";
		
		/* String형의 주소 비교
		 * String형은 readonly. 수정 불가한 타입 
		 * -> 동일한 데이터를 두개이상 저장할 필요 X : 동일한 메모리 주소를 공유
		 * -> 주소 비교해서 같으면 같은(동일한) 변수 */
		if(str == str2) System.out.println("equal");

		// 값 비교
		System.out.println(str.equals(str2));
			
		
	}

	
}
