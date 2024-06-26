package effectiveJava_Enum;

/**
 * Item34_Planet 
 * - 각 상수와 연관된 데이터를 해당 상수 자체에 내장한 열거 타입
 * - 상수 하나당 생성자에서 데이터를 받아 자신의 인스턴스를 만들어 public static final 필드로 공개함
 * 
 * @author ga29
 */
public enum Item34_Planet {
    MERCURY (3.302e+23, 2.439e6),
    VENUS   (4.869e+24, 6.052E6),
    EARTH   (5.975e+24, 6.378E6),
    MARS    (6.419e+23, 3.393e6),
    JUPITER (1.899e+27, 7.149e7),
    SATRUN  (5.685e+26, 6.027e7),
    URANUS  (8.683e+25, 2.556e7),
    NEPTUNE (1.024e+26, 2.477e7);

    private final double mass;              // 질량(단위: 킬로그램)
    private final double radius;            // 반지름(단위: 미터)
    private final double surfaceGravity;    // 표면중력(단위: m / s^2)

    // 중력상수(단위: m^3 / kg s^2)
    private static final double G = 6.67300E-11;

    Item34_Planet(double mass, double radius) {
        this.mass = mass;
        this.radius = radius;
        this.surfaceGravity = G * mass / (radius * radius);
    }

    public double mass() {return mass;}
    public double radius() {return radius;}
    public double surfaceGravity() {return surfaceGravity;}

    public double surfaceWeight(double mass) {
        return mass * surfaceGravity;   // F = ma
    }

}
