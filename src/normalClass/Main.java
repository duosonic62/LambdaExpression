package normalClass;


public class Main {
    public static void main(String[] args) {
        Display display = new Display();
        CalculatorInterface calculator = new AddCalculator();
        display.showCalc(calculator, 3, 5);
    }
}
