package effectiveJava_Enum;

public class Item34_OperationMain {

    public static void main(String[] args) {
        double x = Double.parseDouble("66");
        double y = Double.parseDouble("52");

        for (Item34_Operation op : Item34_Operation.values()) {
            System.out.printf("%f %s %f = %f%n", x, op, y, op.apply(x, y));
        }
    }

}
