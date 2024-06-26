package effectiveJava_Enum;

/**
 * 상수별 동작이 달라야 하는 경우 
 * 
 *  - 시도1. switch-case문
 *    문제 : 문법 때문에 throw 있어야 하며, 새로운 상수마다 case 문 추가해야 한다....
 * 
 *  - 시도2. 상수별 메서드 구현
 *    추상 메서드 선언, 각 상수별 클래스 몸체에서 재정의
 * 
 * @author ga29 
 */
public enum Item34_Operation {
    // PLUS, MINUS, TIMES, DIVIDE;

    // 시도1. switch-case문
    // 시도 1. 
    // public double apply(double x, double y) {
    //     switch (this) {
    //         case PLUS: return x + y;
    //         case MINUS: return x - y;
    //         case TIMES: return x * y;
    //         case DIVIDE: return x / y;
    //     }
    //     throw new AssertionError("알 수 없는 연산: "+this);
    // }


    // 시도2. 상수별 (추상)메서드 구현 + 상수별 데이터
    PLUS("+") {public double apply(double x, double y) {return x + y;}},
    MINUS("-") {public double apply(double x, double y) {return x - y;}},
    TIMES("*") {public double apply(double x, double y) {return x * y;}},
    DIVIDE("/") {public double apply(double x, double y) {return x / y;}};

    private final String symbol;

    Item34_Operation(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public String toString() {return symbol;}

    // 추상 메서드
    public abstract double apply(double x, double y);
}
