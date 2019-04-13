package anonymousInnerClass;

public class Main {
    public static void main(String[] args) {
        Display display = new Display();

        // 一回なら使い捨てで
        CalculatorInterface calculator = new CalculatorInterface() {
            @Override
            public int calc(int a, int b) {
                return a + b;
            }
        };
        display.showCalc(calculator, 3, 5);
    }
}
