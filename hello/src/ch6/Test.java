package ch6;

public class Test {

	public static void main(String args[]) {
		int num1 = 10;
		int num2 = 20;
		adder(num1, num2);
		System.out.println("---------------");
	}

	public static int adder(int num1, int num2) {
		int result = num1 + num2;
		return result;
	}
}
