package innerClass;

import normalClass.AddCalculator;

public class Main {
    public static void main(String[] args) {
        Display display = new Display();

        // ここでしか使わないし・・・
        class AddCalculator implements CalculatorInterface {

            @Override
            public int calc(int a, int b) {
                return a + b;
            }
        }

        CalculatorInterface calculator = new AddCalculator();
        display.showCalc(calculator, 3, 5);
    }
}
