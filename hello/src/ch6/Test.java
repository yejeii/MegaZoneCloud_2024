package ch6;

public class Test {

	public static void main(String args[]) {
		int num1 = 10;
		int num2 = 20;
		adder(num1, num2);
		System.out.println("---------------");
		
		Car car = new Car();
		System.out.println(car.count);
		System.out.println(car.serialNo);
		
		School school = new Elementary(5);
		school.gender = 2;
		System.out.println(school.toString() + ", " + school.getData());
//		System.out.println(school.safety);	불가
		
		Elementary elementary = (Elementary)school;
		System.out.println(elementary.toString() + ", " + elementary.getData());
		elementary.someMethod();
		System.out.println();
	}

	public static int adder(int num1, int num2) {
		int result = num1 + num2;
		return result;
	}
}

class School {
	int teachers;
	int students;
	int gender;
	int education;
	int grade;

	public School() {
		this.grade = 3;
	}

	public String getData() {
		return getClass() + " [teachers + " + teachers + ", students=" + students + ", gender=" + gender + ", education="
				+ education + ", grade=" + grade + "]";
	}
	
	
}

class Elementary extends School {

	int education = 1;
	int safety;

	public Elementary(int safety) {
	    super();
	    this.teachers = 45;
	    this.students = 465;
	    this.grade = 6;
	    this.safety = safety;
	  }

	@Override
	public String getData() {
		return super.getData().substring(0, super.getData().lastIndexOf("]")) + ", safety=" + safety + "]";
	}
	
	public void someMethod() {
		System.out.println("It's a Elementary's method");
	}
}