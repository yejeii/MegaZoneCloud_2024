package effectiveJava_Enum;

public class Item34_WeightTable {

    public static void main(String[] args) {
        double earthWeight = Double.parseDouble("58");
        double mass = earthWeight / Item34_Planet.EARTH.surfaceGravity();
    
        for (Item34_Planet planet : Item34_Planet.values()) {
            System.out.printf("%s에서 무게는 %f이다%n", planet, planet.surfaceWeight(mass));
        }
    }
}
